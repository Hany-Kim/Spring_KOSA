package com.study.schedular.ms.dao;

import com.study.schedular.ms.model.MemberReserveJoin;

public interface IMemberReserveJoinRepository {
	int getMaxJoinId(); // Join table 최대 id값 조회
	
	void insertMemberReserveJoin(MemberReserveJoin mrj); // 신규 member, reserve추가
	
	int deleteJoin(int userId, int reserveId);
}
