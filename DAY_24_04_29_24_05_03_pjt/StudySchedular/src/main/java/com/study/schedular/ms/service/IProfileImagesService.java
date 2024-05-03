package com.study.schedular.ms.service;

import com.study.schedular.ms.model.ProfileImages;

public interface IProfileImagesService {
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
	String getCategoryName(int fileId);
	void updateCategory(int[] fileIds, String categoryName);
	
	void uploadFile(ProfileImages file);
	ProfileImages getFile(int fileId);
	String getUuidFileName(int fileId);
	void deleteFile(int fileId);
}
