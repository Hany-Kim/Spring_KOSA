package com.study.schedular.ms.dao;

import java.util.List;

import com.study.schedular.ms.model.Member;
import com.study.schedular.ms.model.Reserve;

public interface IMemberRepository {
	int getMaxMemberId(); // member table 최대 id값 조회
	
	String getMemberId(Member member); // 이미 있는 member여부 확인
	void insertMember(Member member); // 신규 member추가
	void updateMember(int userId); // 미구현(4/30 5:54)
	
	List<Member> getAllMembers(); // 전체 멤버 조회
    List<Member> searchMembersByName(String name); // 이름으로 멤버 조회
	Member getUserInfo(int userId);		// 유저정보 조회
	
	String getName(String phone);		// 유저 핸드폰번호 조회(로그인용)
	String getPassword(String phone); // 유저 패스워드 조회(로그인용)
	String getProfile(int userId);
	String getPhone(int userId); // 멤버 폰번호 가져요기
	
	// 우석님작업중
	List<Reserve> getFutureReserveInfo(int userId); // 미래예약정보를 위해
	List<Reserve> getPastReserveInfo(int userId); // 과거예약정보를 위해
	void updatePast(Reserve reserve); //구현중
	void updateFuture(Reserve reserve); //구현중

	void updateMember(Member member); // 실제적인 업데이트(프로필화면에서)
	Reserve oneFutureReserveInfo(int reserveId); // 미래예약정보 작성중
	Reserve onePastReserveInfo(int reserveId); // 과거예약정보 작성중
}
