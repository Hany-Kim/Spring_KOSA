package com.sample.myapp.member;

public class MemberController {
	IMemberService memberService;
	
	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}
	
	public void printInfo() {
		Member member = memberService.getMemberInfo();
		System.out.println(member);
	}
	
}
