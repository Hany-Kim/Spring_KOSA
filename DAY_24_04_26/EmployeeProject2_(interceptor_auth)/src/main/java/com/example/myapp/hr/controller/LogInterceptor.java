package com.example.myapp.hr.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // Controller를 실행하기 직전
			throws Exception {
		System.out.println("컨트롤러를 실행하기 전에 실행합니다.");
		String uri = request.getRequestURI();
		String now = new Date().toString();
		System.out.println("URI: " + uri);
		System.out.println("현재시간: " + now);
		return true; // return false면 인터셉터가 종료됨
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, // Controller를 실행한 다음, 뷰를 처리하기 직전
			ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러를 실행한 후에 실행합니다. (뷰 실행 전)");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) // 뷰까지 실행한 다음
			throws Exception {
		System.out.println("뷰가 실행된 후 실행됩니다.");

	}

}
