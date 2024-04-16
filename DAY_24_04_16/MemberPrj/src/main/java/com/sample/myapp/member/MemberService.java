package com.sample.myapp.member;

import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

	@Override
	public Member getMemberInfo() {
		Member member = new Member();
		member.setMemberId("1234");
		member.setName("홍길동");
		member.setAge(30);
		member.setEmail("0000@gmail.com");
		return member;
	}

}
