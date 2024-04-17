package com.example.myapp.aop;

public interface IHelloService {
	// 인터페이스 내 접근 권한은 public안 써도 public임
	String sayHello(String name);
	String sayGoodbye(String name);
}
