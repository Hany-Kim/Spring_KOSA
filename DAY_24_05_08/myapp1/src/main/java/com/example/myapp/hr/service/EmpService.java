package com.example.myapp.hr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.hr.dao.IEmpRepository;

@Service
public class EmpService implements IEmpService {

	@Autowired
	IEmpRepository empRepository;
	
	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public Map<String, Object> getEmpInfo(int empid) {
		return empRepository.getEmpInfo(empid);
	}

	@Override
	public List<Map<String, Object>> getEmpList() {
		return empRepository.getEmpList();
	}

}
