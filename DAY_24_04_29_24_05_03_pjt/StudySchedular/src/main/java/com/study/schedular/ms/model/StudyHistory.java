package com.study.schedular.ms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class StudyHistory {
	private int reserveId;
	private int studyId;
	private String captainName;
	private String captainPhone;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String studyTitle; // profile
	private String studyContent;
	
	private String start;
	private String end;
	private int userId;
}
