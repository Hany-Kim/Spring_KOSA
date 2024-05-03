package com.study.schedular.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.schedular.ms.dao.IMemberReserveJoinRepository;
import com.study.schedular.ms.model.MemberReserveJoin;

@Service
public class MemberReserveJoinService implements IMemberReserveJoinService {

	@Autowired
	IMemberReserveJoinRepository memberReserveJoinRepository;
	
	@Override
	public int getMaxJoinId() {
		return memberReserveJoinRepository.getMaxJoinId();
	}

	@Override
	public void insertMemberReserveJoin(MemberReserveJoin mrj) {
		memberReserveJoinRepository.insertMemberReserveJoin(mrj);
	}

	@Override
	public int deleteJoin(int userId, int reserveId) {
		return memberReserveJoinRepository.deleteJoin(userId, reserveId);
	}

}
