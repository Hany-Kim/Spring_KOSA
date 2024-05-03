package com.study.schedular.ms.common.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("컨트롤러를 실행하기 전에 실행합니다.");
		String uri = request.getRequestURI();
		String now = new Date().toString();
		System.out.println("URI: " + uri);
		System.out.println("현재시간: " + now);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러 실행 후 실행합니다.(뷰 실행 전)");
		System.out.println(modelAndView.getViewName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("뷰가 실행된 후 실행됩니다.");
	}

}
