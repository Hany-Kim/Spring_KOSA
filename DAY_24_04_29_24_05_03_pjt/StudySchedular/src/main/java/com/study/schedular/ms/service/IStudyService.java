package com.study.schedular.ms.service;

import java.util.List;

import com.study.schedular.ms.model.Study;

public interface IStudyService {
	List<Study> getAllStudies(); // 전체 스터디 조회
    List<Study> searchStudiesByName(String studyName); // 이름으로 스터디 조회
	Study getStudyInfo(int studyId);		// 유저정보 조회
	String getStudyUri(int studyId);
}
