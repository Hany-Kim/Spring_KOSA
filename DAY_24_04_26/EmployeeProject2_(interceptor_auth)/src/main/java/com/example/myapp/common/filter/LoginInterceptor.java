package com.example.myapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// javax => tomcat 9버전
// 자카르타 => tomcat 10버전

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 메서드 실행 전
		HttpSession session = request.getSession();
		try {
			// 세션에 어떤 정보가 있는지 확인한 후, 정보가 없으면 로그인 페이지로 Redirect해야 함.
			String email = (String) session.getAttribute("email"); // 만약에 로그인 되어있다면 그 사람의 email을 받음
			if(email == null || email.equals("")) {
				response.sendRedirect(request.getContextPath() + "/member/login");
			}
		} catch (Exception e) {
			e.printStackTrace(); // 예외 경로 출력해서 로그로 남김
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 메서드실행 후, 뷰 실행 전
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰 실행 후
		
	}

}
