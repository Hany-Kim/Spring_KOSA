package com.example.myapp.upload.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.upload.dao.IUploadFileRepository;
import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.model.UploadFileDto;

@Service
public class UploadFileService implements IUploadFileService {

	@Autowired
	IUploadFileRepository uploadFileRepository;
	
	
	// DB에 파일 업로드
	@Override
	public void uploadFile(UploadFile file) {
		int newFileId = uploadFileRepository.getMaxFileId() + 1;
		file.setFileId(newFileId);
		uploadFileRepository.uploadFile(file);
	}

	@Override
	public List<UploadFile> getFileList(String category) {
		return uploadFileRepository.getFileList(category);
	}

	@Override
	public List<UploadFile> getAllFileList() {
		return uploadFileRepository.getAllFileList();
	}

	@Override
	public List<UploadFile> getImageList(String category) {
		return uploadFileRepository.getImageList(category);
	}

	@Override
	public UploadFile getFile(int fileId) {
		return uploadFileRepository.getFile(fileId);
	}

	@Override
	public String getCategoryName(int fileId) {
		return uploadFileRepository.getCategoryName(fileId);
	}

	@Override
	@Transactional
	public void updateCategory(int[] fileIds, String categoryName) {
		for(int fileId : fileIds) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fileId", fileId);
			map.put("categoryName", categoryName);
			uploadFileRepository.updateCategory(map);
		}
	}

	@Override
	public void deleteFile(int fileId) {
		uploadFileRepository.deleteFile(fileId);
	}
	
	
	// --- 파일 시스템을 사용한 파일 업로드----------------------------
	@Override
	public void uploadFile2(UploadFileDto file) {
		int newFileId = uploadFileRepository.getMaxFileId2() + 1;
		file.setFileId(newFileId);
		uploadFileRepository.uploadFile2(file);
	}

	
	@Override
	public List<UploadFileDto> getAllFileList2() {
		return uploadFileRepository.getAllFileList2();
	}
	
	@Override
	public UploadFileDto getFile2(int fileId) {
		return uploadFileRepository.getFile2(fileId);
	}
	
	@Override
	public String getUuidFileName(int fileId) {
		return uploadFileRepository.getUuidFileName(fileId);
	}
	
	@Override
	public void deleteFile2(int fileId) {
		uploadFileRepository.deleteFile2(fileId);
	}
}
