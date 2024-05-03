package com.study.schedular.ms.dao;

import com.study.schedular.ms.model.Reserve;

public interface IReserveRepository {
	int getMaxReserveId();
	
	String getReserveId(Reserve reserve); // 이미 있는 예약정보인지
	void insertReserve(Reserve reserve); // 신규 예약추가
	int getStudyId(int reserveId);
	
	void updateReserve(Reserve reserve); // 예약 수정
	
	int deleteReserve(int reserveId);
}
