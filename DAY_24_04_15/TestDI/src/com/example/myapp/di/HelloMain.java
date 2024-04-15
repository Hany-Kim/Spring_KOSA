package com.example.myapp.di;

public class HelloMain {
	public static void main(String[] args) {
		IHelloService helloService = new HelloService();
		HelloController controller = new HelloController();
		controller.setHelloService(helloService);
//		HelloController controller = new HelloController(helloService);
		
		controller.hello("홍길동");
	}
}
