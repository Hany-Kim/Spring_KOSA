package com.study.schedular.ms.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Reserve {
	private int reserveId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp startDate;
	private Timestamp endDate;
	private String content;
	private int studyId; // 외래키
	private String captainName;
	private String captainPhone;
	
	private String start;
	private String end;
	
	private String studyName;
}
