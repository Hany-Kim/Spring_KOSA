package com.example.myapp.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {

	// 핵심 코드
	/* 핵심 코드 안에 공통 코드가 포함된 코드이다 
	 * 나중엔 이렇게 짜면 안됨
	 * */
	@Override
	public String sayHello(String name) {
		//System.out.println(">>>LOG : " + new java.util.Date()); // 공통 코드
		//HelloLog.log(); // 횡단적 산재가 남음
		System.out.println("HelloService.sayHello() 실행");
		
		/* after-throwing */
		int rand = (int)(Math.random() * 10); // 0~10까지 랜덤 수
		if(rand < 5) {
			throw new RuntimeException("강제예외 발생"); // 강제로 예외 발생시킴
		}
		
		return "Hello~ " + name;
	}

	@Override
	public String sayGoodbye(String name) {
		return "Bye~ " + name;
	}

}
