package com.study.schedular.ms.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.schedular.ms.dao.IProfileImagesRepository;
import com.study.schedular.ms.model.ProfileImages;


@Service
public class ProfileImagesService implements IProfileImagesService {
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
//	쓰지 마세요. 버릴 파일이에요
	@Autowired
	IProfileImagesRepository uploadFileRepository;
	
	// DB에 파일 업로드
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
	
	
	// --- 파일 시스템을 사용한 파일 업로드----------------------------
	@Override
	public void uploadFile(ProfileImages file) {
		int newFileId = uploadFileRepository.getMaxFileId() + 1;
		file.setFileId(newFileId);
		uploadFileRepository.uploadFile(file);
	}
	
	@Override
	public ProfileImages getFile(int fileId) {
		return uploadFileRepository.getFile(fileId);
	}
	
	@Override
	public String getUuidFileName(int fileId) {
		return uploadFileRepository.getUuidFileName(fileId);
	}
	
	@Override
	public void deleteFile(int fileId) {
		uploadFileRepository.deleteFile(fileId);
	}
}
