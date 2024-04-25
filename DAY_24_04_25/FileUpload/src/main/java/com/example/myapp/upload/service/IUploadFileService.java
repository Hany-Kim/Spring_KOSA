package com.example.myapp.upload.service;

import java.util.List;

import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.model.UploadFileDto;

public interface IUploadFileService {
	void uploadFile(UploadFile file);
	
	List<UploadFile> getFileList(String category);
	List<UploadFile> getAllFileList();
	List<UploadFile> getImageList(String category);
	
	UploadFile getFile(int fileId);
	
	String getCategoryName(int fileId);
	void updateCategory(int[] fileIds, String categoryName);
	
	void deleteFile(int fileId);
	
	void uploadFile2(UploadFileDto file);
	List<UploadFileDto> getAllFileList2();
	UploadFileDto getFile2(int fileId);
	String getUuidFileName(int fileId);
	void deleteFile2(int fileId);

}
