package com.example.myapp.upload.dao;

import java.util.HashMap;
import java.util.List;

import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.model.UploadFileDto;

public interface IUploadFileRepository {
	int getMaxFileId();
	void uploadFile(UploadFile file);
	
	List<UploadFile> getFileList(String categoryName);
	List<UploadFile> getAllFileList();
	List<UploadFile> getImageList(String categoryName);
	
	UploadFile getFile(int fileId);
	
	String getCategoryName(int fileId);
	void updateCategory(HashMap<String, Object> map);
	
	void deleteFile(int fileId);
	
	int getMaxFileId2();
	void uploadFile2(UploadFileDto file);
	List<UploadFileDto> getAllFileList2();
	UploadFileDto getFile2(int fileId);
	String getUuidFileName(int fileId);
	void deleteFile2(int fileId);
}
