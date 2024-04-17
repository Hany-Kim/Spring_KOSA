package com.example.myapp.aop;

public class HelloServiceProxy extends HelloService {
	
	@Override
	public String sayHello(String name) {
		HelloLog.log();
		return super.sayHello(name);
	}
}
