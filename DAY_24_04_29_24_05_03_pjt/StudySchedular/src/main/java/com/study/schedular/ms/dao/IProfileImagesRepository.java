package com.study.schedular.ms.dao;

import java.util.HashMap;

import com.study.schedular.ms.model.ProfileImages;


public interface IProfileImagesRepository {
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
	String getCategoryName(int fileId);
	void updateCategory(HashMap<String, Object> map);
	
	int getMaxFileId();
	void uploadFile(ProfileImages file);
	ProfileImages getFile(int fileId);
	String getUuidFileName(int fileId);
	void deleteFile(int fileId);
}
