package com.study.schedular.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.schedular.ms.dao.IMemberRepository;
import com.study.schedular.ms.model.Member;
import com.study.schedular.ms.model.Reserve;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberRepository memberRepository;
	
	@Override
	public int getMaxMemberId() {
		return memberRepository.getMaxMemberId();
	}

	@Override
	public String getMemberId(Member member) {
		return memberRepository.getMemberId(member);
	}

	@Override
	public void insertMember(Member member) {
		memberRepository.insertMember(member);
	}
	
	@Override
	public void updateMember(int userId) {
		memberRepository.updateMember(userId);
	}
	
	@Override
	public List<Member> getAllMembers() {
		return memberRepository.getAllMembers();
	}

	@Override
	public List<Member> searchMembersByName(String name) {
		return memberRepository.searchMembersByName(name);
	}

	@Override
	public Member getUserInfo(int userId) {
		return memberRepository.getUserInfo(userId);
	}

	@Override
	public String getName(String phone) {
		return memberRepository.getName(phone);
	}

	@Override
	public String getPassword(String phone) {
		return memberRepository.getPassword(phone);
	}

	@Override
	public String getPhone(int userId) {
		return memberRepository.getPhone(userId);
	}

	// 우석님 작업중
	@Override
	public List<Reserve> getFutureReserveInfo(int userId) {
		return memberRepository.getFutureReserveInfo(userId);
	}
	
	@Override
	 public List<Reserve> getPastReserveInfo(int userId){
	 	return memberRepository.getPastReserveInfo(userId);
	 }



	@Override
	public void updateMember(Member member) {
		memberRepository.updateMember(member);
	}
	
	 @Override
	 public Reserve onePastReserveInfo(int reserveId){
	 	return memberRepository.onePastReserveInfo(reserveId);
	 }

	@Override
	public Reserve oneFutureReserveInfo(int reserveId) {
		return memberRepository.oneFutureReserveInfo(reserveId);
	}

	@Override
	public void updateFuture(Reserve reserve) {
		// TODO Auto-generated method stub
		 memberRepository.updateFuture(reserve);

	}
	@Override
	public void updatePast(Reserve reserve) {
		memberRepository.updatePast(reserve);
	}
	
	@Override
	public String getProfile(int userId) {
		return memberRepository.getProfile(userId);
	}
}
