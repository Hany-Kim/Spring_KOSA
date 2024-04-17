package com.example.myapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class HelloLog {
	@Pointcut(value="execution(* com.example..*.sayHello(..))") // <aop:pointcut expression="execution(* com.example..HelloService.sayHello(..))" id="hello"/>
	private void helloPointcut() {}
	
//	public static void log() { // Proxy에서 바로 호출해서 사용하려고 static 붙임
//		System.out.println(">>>LOG<<< : " + new java.util.Date());
//	}
	
	@Before("helloPointcut()") // private void helloPointcut() {} 호출
	public void log(JoinPoint joinpoint) { // Proxy에서 바로 호출해서 사용하려고 static 붙임
		Signature signature = joinpoint.getSignature();
		String methodName = signature.getName(); // sayHello
//		String methodName = signature.toShortString(); // IHelloService.sayHello(..)
//		String methodName = signature.toLongString(); // public abstract java.lang.String com.example.myapp.aop.IHelloService.sayHello(java.lang.String)
		
		System.out.println(">>>LOG<<< : 핵심코드 메서드명 - " + methodName);
		System.out.println(">>>LOG<<< : " + new java.util.Date());
	}
	
	public void resultLog(JoinPoint joinPoint, Object resultObj) { // Object : 어떤 타입으로 반환될지 모를 때
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		System.out.println(">>>LOG<<< : 핵심코드 메서드명 - " + methodName);
		System.out.println("핵심코드의 반환 값 : " + resultObj.toString());
	}
	
	public void exceptionLog(JoinPoint joinPoint, Exception exception) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		System.out.println(">>>EXCEPTION LOG<<< : 핵심코드 메서드 명 - " + methodName);
		System.out.println("예외발생 - 메시지 : " + exception.getMessage());
	}
	
	public Object aroundLog(ProceedingJoinPoint joinPoint) { // 항상 반환 되는 값이 있어야함
		// ProceedingJoinPoint is a JoinPoint => 항상 선언되어 있어야 함
		Object result = null;
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		System.out.println(">>>BEFORE LOG<<< : 메서드 이름 - " + methodName);
		try {
			result = joinPoint.proceed(); // 핵심코드를 실행 => 핵심코드가 실행된 것이 반환됨
		} catch(Throwable e) {
			System.out.println(">>>EXCEPTION LOG<<< : 예외 메시지 - " + e.getMessage());
		} finally {
			System.out.println(">>>FINALLY<<<");
		}
		
		return result;
	}
}
