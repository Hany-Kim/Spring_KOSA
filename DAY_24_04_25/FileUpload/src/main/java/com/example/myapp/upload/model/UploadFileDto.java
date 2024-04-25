package com.example.myapp.upload.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class UploadFileDto {
	private int fileId;
	private String categoryName;
	private String fileName;
	private String uuidFileName; // 파일시스템에 실제 저장할 파일 이름
	private long fileSize;
	private String fileContentType;
	private Timestamp fileUploadDate;
}
