package com.study.schedular.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.schedular.ms.dao.IStudyRepository;
import com.study.schedular.ms.model.Study;

@Service
public class StudyService implements IStudyService {
	
	@Autowired
	IStudyRepository studyrepository;
	
	@Override
	public List<Study> getAllStudies() {
		return studyrepository.getAllStudies();
	}

	@Override
	public List<Study> searchStudiesByName(String studyName) {
		return studyrepository.searchStudiesByName(studyName);
	}

	@Override
	public Study getStudyInfo(int studyId) {
		return studyrepository.getStudyInfo(studyId);
	}

	@Override
	public String getStudyUri(int studyId) {
		return studyrepository.getStudyUri(studyId);
	}
}
