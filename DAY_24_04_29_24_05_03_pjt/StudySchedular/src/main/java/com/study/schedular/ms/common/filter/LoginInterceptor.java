package com.study.schedular.ms.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		try {
			// Session의 정보를 확인 후 , 정보가 없으면 로그인 페이지로 리다이렉트
			String name = (String) session.getAttribute("name");
			if(name == null || name.equals("")) {
				// 이전 URL 정보 저장
				String reqUrl = request.getRequestURI();
	            String queryString = request.getQueryString(); // 쿼리 스트링이 있다면 가져온다.
	            if (queryString != null) {
	                reqUrl += "?" + queryString;
	            }
	            session.setAttribute("prevPage", reqUrl);
				response.sendRedirect(request.getContextPath() + "/ms/login");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
