package com.example.myapp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.member.dao.IMemberRepository;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberRepository memberRepository;
	
	@Override
	public String getPassword(String userid) {
		return memberRepository.getPassword(userid);
	}

	@Override
	public String getEmail(String userid) {
		return memberRepository.getEmail(userid);
	}

}
