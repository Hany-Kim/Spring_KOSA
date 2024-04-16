package com.example.myapp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {

	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("application-config.xml");
//		AbstractApplicationContext context =
//				new AnnotationConfigApplicationContext("application-config.xml");
		
		HelloController controller = context.getBean(HelloController.class);
		controller.hello("홍길동");
		HelloController controller2 = context.getBean(HelloController.class);
		controller2.hello("홍길서");
		
		System.out.println(controller);
		System.out.println(controller2);
		System.out.println(controller==controller2);
		context.close();

	}

}
