package com.study.schedular.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.schedular.ms.dao.IReserveRepository;
import com.study.schedular.ms.model.Reserve;

@Service
public class ReserveService implements IReserveService {

	@Autowired
	IReserveRepository reserveRepository;
	
	@Override
	public int getMaxReserveId() {
		return reserveRepository.getMaxReserveId();
	}
	
	@Override
	public String getReserveId(Reserve reserve) {
		return reserveRepository.getReserveId(reserve);
	}

	@Override
	public void insertReserve(Reserve reserve) {
		reserveRepository.insertReserve(reserve);
	}

	@Override
	public int getStudyId(int reserveId) {
		return reserveRepository.getStudyId(reserveId);
	}

	@Override
	public void updateReserve(Reserve reserve) {
		reserveRepository.updateReserve(reserve);
	}

	@Override
	public int deleteReserve(int reserveId) {
		return reserveRepository.deleteReserve(reserveId);
	}
	
	

}
