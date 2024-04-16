package com.example.myapp;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("helloService.sayHello()");
		String message = "Hello~~ " + name;
		return message;
	}

}
