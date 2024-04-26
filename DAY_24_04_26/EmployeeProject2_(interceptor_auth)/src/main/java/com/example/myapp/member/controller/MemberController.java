package com.example.myapp.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.myapp.member.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/login")
	public String login(String userid, String password, HttpSession session) {
		if(userid == null) {
			return "member/login";
		} else {
			// userid로 데이터베이스에 저장되어 있는 비밀 번호 조회
			String dbPassword = memberService.getPassword(userid);
			// 데이터베이스 비밀번호와 사용자가 입력한 비밀번호를 비교해서
			if(dbPassword!=null && !dbPassword.equals("")) {
				// 사용자 ID가 있는 경우
				if(dbPassword.equals(password)) {
					// 로그인 성공한 경우
					// 같으면 세션에 정보를 저장
					// 사용자의 이메일을 조회하는 코드 <= 직접 구현
					String userEmail = memberService.getEmail(userid);
					session.setAttribute("email", userEmail);
					
				} else {
					// 아이디는 있지만 비밀번호가 다른 경우
					return "member/login";
				}
			}else {
				// 아이디가 없는 경우
				return "member/login";
			}
		}
		return "redirect:/member/login";
	}
	
	@GetMapping("member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login";
	}
}
