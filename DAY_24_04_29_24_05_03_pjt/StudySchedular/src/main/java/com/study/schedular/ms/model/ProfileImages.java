package com.study.schedular.ms.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;

@Setter @Getter
@ToString
public class ProfileImages {
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
	private int fileId;
	private String categoryName;
	private String fileName;
	private String uuidFileName;
	private long fileSize;
	private String fileContentType;
	private Timestamp fileUploadDate;
}
