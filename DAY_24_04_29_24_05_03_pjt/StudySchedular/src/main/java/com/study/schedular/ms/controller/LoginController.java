package com.study.schedular.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.schedular.ms.model.Member;
import com.study.schedular.ms.service.IMemberService;

@Controller
public class LoginController {
	
	@Autowired
	IMemberService memberService;
	
	@GetMapping("ms/login")
	public String login() {
		return "ms/login";
	}
	
	@PostMapping("ms/login")
	public String login(String phone, String password, HttpSession session) {
		if (session.getAttribute("name") != null) {
			return "redirect:/";
		} else {
			if(phone == null) {
				session.setAttribute("loginError", "전화번호를 입력하세요.");
				return "ms/login";
			}else {
				// userid로 데이터베이스의 비밀번호 조회
				String dbPassword = memberService.getPassword(phone);
				// database의 비밀번호와 사용자가 입력한 비밀번호를 비교한다.
				if(dbPassword != null && !dbPassword.equals("")) {
					// 사용자 아이디가 있는 경우
					if(dbPassword.equals(password)) {
						// 로그인 성공한 경우
						// 같으면 세션에 정보를 저장
						// 사용자의 이메일을 조회하는 코드
						session.removeAttribute("loginError"); // 성공 시 오류 메시지 삭제
						String userName = memberService.getName(phone);
						Member m = new Member();
						m.setName(userName);
						m.setPhone(phone);
						String userId = memberService.getMemberId(m);
						session.setAttribute("name", userName);
						session.setAttribute("userId", userId);
						String prevPage = (String) session.getAttribute("prevPage");
					    if(prevPage != null && !prevPage.isEmpty()) {
					        session.removeAttribute("prevPage"); // 사용했으므로 세션에서 삭제
					        return "redirect:" + prevPage; // 저장된 이전 페이지 URL로 리다이렉트
					    } else {
					        return "redirect:/"; // 이전 페이지 정보가 없다면 기본 페이지로 리다이렉트
					    }
					} else {
						// 아이디는 있지만 비밀번호가 다른 경우
						session.setAttribute("loginError", "비밀번호가 일치하지 않습니다.");
						return "ms/login";
					}
				} else {
					// 비밀번호가 없거나 데이터베이스의 비밀번호와 다른 경우
					session.setAttribute("loginError", "계정을 찾을 수 없습니다.");
					return "ms/login";
				}
			}
		}
	}
	
	@GetMapping("/ms/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate(); // session 무효로 함
		return "redirect:/";
	}
}
