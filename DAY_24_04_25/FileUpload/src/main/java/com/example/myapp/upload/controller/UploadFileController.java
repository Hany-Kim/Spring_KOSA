package com.example.myapp.upload.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.model.UploadFileDto;
import com.example.myapp.upload.service.IUploadFileService;

@Controller
public class UploadFileController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IUploadFileService uploadService;
	
	@GetMapping(value="/file/new")
	public String uploadFile(Model model) {
		logger.info("파일 입력 양식을 요청합니다.");
		return "file/form";
	}
	
	@PostMapping(value="/file/new")
	public String uploadFile(@RequestParam(value="category", required=false,
defaultValue="/") String category, @RequestParam MultipartFile file,
RedirectAttributes redirectAttrs) {
		logger.info(category);
		logger.info(file.getOriginalFilename());
		try {
			if(file!=null && !file.isEmpty()) {
				UploadFile newFile = new UploadFile();
				newFile.setCategoryName(category);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileData(file.getBytes());
				uploadService.uploadFile(newFile);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/list")
	public String getFileList(Model model) {
		model.addAttribute("fileList", uploadService.getAllFileList());
		return "file/list";
	}
	
	@GetMapping("/file/list/gallery")
	public String getImageList(@RequestParam(value="category", required=false,
defaultValue="image")String category, Model model) {
		model.addAttribute("fileList", uploadService.getImageList(category));
		return "file/gallery";
	}
	
	@GetMapping("/file/list/{category}")
	public String getFileListByCategory(@PathVariable String category, Model model) {
		model.addAttribute("fileList", uploadService.getFileList(category));
		return "file/list";
	}
	
	@GetMapping("/file/{fileId}")
	public ResponseEntity<byte[]> getBinaryFile(@PathVariable int fileId) throws UnsupportedEncodingException{
		UploadFile file = uploadService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			logger.info("getFile" + file.toString());
			String[] mtypes = file.getFileContentType().split("/"); // primary타입과 서브타입을 /로 구분 => text/html == primary타입/서브타입
			headers.setContentType(new MediaType(mtypes[0], mtypes[1])); // 미디어 타입을 지정
			headers.setContentLength(file.getFileSize()); // 컨텐츠 크기
			try {
				String encodedFileName = URLEncoder.encode(file.getFileName(), "UTF-8");
				headers.setContentDispositionFormData("attachment", encodedFileName); // attachment : 첨부파일 이름 지정
//				headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8")); // attachment : 첨부파일 이름 지정
			} catch (UnsupportedEncodingException e) {
				logger.error("지원하지 않는 인코딩입니다.");
			}
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
		uploadService.deleteFile(fileId);
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/category/update")
	public String updateCategory(@RequestParam int[] fileIds, @RequestParam String categoryName) {
		uploadService.updateCategory(fileIds, categoryName);
		return "redirect:/file/list";
	}
	
	@GetMapping(value="/file2/new")
	public String uploadFile2() {
		logger.info("파일 입력 양식을 요청합니다.");
		return "file/form";
	}
	
	@PostMapping(value="/file2/new")
	public String uploadFile2(String category, MultipartFile file) {
		logger.info(category);
		logger.info(file.getOriginalFilename());
		try {
			if(file!=null && !file.isEmpty()) {
				// uuid 파일명 생성
				String fileName = file.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf("."));
				UUID uuid = UUID.randomUUID();
				String uuidFileName = uuid + fileExt;
				
				// 파일 저장
				File saveFilePath = new File("C:/dev/upload", uuidFileName);
				file.transferTo(saveFilePath);
				
				UploadFileDto newFile = new UploadFileDto();
				newFile.setCategoryName(category);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setUuidFileName(uuidFileName);
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
//				newFile.setFileData(file.getBytes());
				uploadService.uploadFile2(newFile);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/file2/list";
	}
	
	@GetMapping("/file2/list")
	public String getFileList2(Model model) {
		List<UploadFileDto> fileList = uploadService.getAllFileList2();
		model.addAttribute("fileList", fileList);
		return "file/list";
	}
	
	@GetMapping("/file2/{fileId}")
	public ResponseEntity<byte[]> getBinaryFile2(@PathVariable int fileId) throws UnsupportedEncodingException{
		UploadFileDto file = uploadService.getFile2(fileId);
		if(file != null) {
			FileInputStream fis = null;
			DataInputStream dis = null;
			try {
				String uuidFileName = file.getUuidFileName();
				File downFile = new File("c:/dev/upload/", uuidFileName);
				fis = new FileInputStream(downFile);
				/*bis = new BufferedInputStream(fis);
				byte[] data = bis.readAllBytes(); // JDK 9버전부터
*/				byte[] buffer = new byte[fis.available()]; // 파일 크기만큼 배열 생성
				dis = new DataInputStream(fis);
				dis.readFully(buffer);
				
				HttpHeaders headers = new HttpHeaders();
				String[] mtypes = file.getFileContentType().split("/"); // primary타입과 서브타입을 /로 구분 => text/html == primary타입/서브타입
				headers.setContentType(new MediaType(mtypes[0], mtypes[1])); // 미디어 타입을 지정
				headers.setContentLength(file.getFileSize()); // 컨텐츠 크기
				try {
					String encodedFileName = URLEncoder.encode(file.getFileName(), "UTF-8");
					headers.setContentDispositionFormData("attachment", encodedFileName); // attachment : 첨부파일 이름 지정
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
				return new ResponseEntity<byte[]>(buffer, headers, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			} finally {
				try {dis.close();} catch(Exception e) {}
			}
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/file2/delete/{fileId}")
	public String deleteFile2(@PathVariable int fileId) {
		String uploadDir = "C:/dev/upload/";
		String uuidFileName = uploadService.getUuidFileName(fileId);
		File file = new File(uploadDir, uuidFileName);
		boolean isDeleted = file.delete();
		if(isDeleted) {
			uploadService.deleteFile2(fileId);
		} else {
			throw new RuntimeException("file not deleted");
		}
		return "redirect:/file2/list";
	}
}
