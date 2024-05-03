package com.study.schedular.ms.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.UUID;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.schedular.ms.model.Member;
import com.study.schedular.ms.model.MemberReserveJoin;
import com.study.schedular.ms.model.Reserve;
import com.study.schedular.ms.service.IMemberReserveJoinService;
import com.study.schedular.ms.service.IMemberService;
import com.study.schedular.ms.service.IReserveService;
import com.study.schedular.ms.service.IStudyService;
import com.study.schedular.ms.service.IProfileImagesService;

@Controller
public class ManageController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	IReserveService reserveService;
	
	@Autowired
	IProfileImagesService profileImagesService;
	
	@Autowired
	IMemberReserveJoinService mrjService;
	
	@Autowired
	IStudyService studyService;
	
	
	@GetMapping("/reserve/reserveform")
	public String createReserve(Model model) {
		model.addAttribute("studyList", studyService.getAllStudies());
		return "reserve/reserveform";
	}
	
	@PostMapping("/reserve/reserveform")
	public String createReserve( // 스터디 일정 예약
			Reserve reserve,
			RedirectAttributes redirectAttributes,
			HttpSession session ) throws Exception {
		/*reserveId=0, startTime=2024-04-29 12:00:00.0, endTime=2024-04-29 13:00:00.0, 
		 * startDate=2024-04-29 12:00:00.0, endDate=2024-04-29 13:00:00.0, 
		 * content=dfdsfds, studyId=0, captainName=세션에서 방장정보 받기, captainPhone=01011112222, 
		 * start=2024-04-29T12:00, end=2024-04-29T13:00*/
		// 세션에서 정보 가져옴
		String name = session.getAttribute("name").toString();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		MemberReserveJoin mrj = new MemberReserveJoin();
		
		reserve = mySetReserve(reserve, name, userId);
		mrj = mySetMRJ(mrj, userId, reserve.getReserveId());
		
		try {
			if(reserve.getContent() == null) {
				String content = "계획된 내용이 없습니다.";
				reserve.setContent(content);
			}
			
			reserveService.insertReserve(reserve);
			mrjService.insertMemberReserveJoin(mrj);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "home";
	}
	
	private MemberReserveJoin mySetMRJ(MemberReserveJoin mrj, int userId, int reserveId) {
		int mrjId = mrjService.getMaxJoinId() + 1;
		
		mrj.setJoinId(mrjId);
		mrj.setUserId(userId);
		mrj.setReserveId(reserveId);
		
		return mrj;
	}
	
	@GetMapping("/member/memberform")
	public String joinMember() {
		return "member/memberform";
	}
	
	@PostMapping("/member/memberform")
	public String joinMember( // 신규 멤버 등록
			Member member,
			@RequestParam(value="category", required=false, defaultValue="/") String category,
			@RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes,
			HttpSession session) throws Exception {
		member = mySetMemberId(member);
		try {
			if(memberService.getMemberId(member) == null) {
				// 존재하지 않는 member
				if(file!=null && !file.isEmpty()) {
					// 파일이 없다면
					String uploadDir = session.getServletContext().getRealPath("/resources/images/user");
					String fileName = file.getOriginalFilename();
					String fileExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					UUID uuid = UUID.randomUUID();
					String uuidFileName = uuid + fileExt;
					
					File saveFilePath = new File(uploadDir, uuidFileName); // 파일 저장할 경로
					
					uploadDir = uploadDir + "/" + uuidFileName; // 파일 업로드할 경로
					
					String savedUploadDir = "/images/user/" + uuidFileName;
					member.setProfile(savedUploadDir);
					
					// Image Upload
	                file.transferTo(saveFilePath);
					memberService.insertMember(member);
				}
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect: /";
	}
	
	private Member mySetMemberId(Member member) {
		int userId = 0;
		try {
			userId = memberService.getMaxMemberId() + 1;
			member.setUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	private Reserve parsingDateTime(Reserve reserve) {
		String[] startDateTime = reserve.getStart().split("T");
		String[] endDateTime = reserve.getEnd().split("T");
		
		String sDate = startDateTime[0];
		String sTime = startDateTime[1];
		sTime = sTime + ":00.0";
		
		String eDate = endDateTime[0];
		String eTime = endDateTime[1];
		eTime = eTime + ":00.0";
		
		String startTimeStamp = sDate + " " + sTime;
		String endTimeStamp = eDate + " " + eTime;
		
		Timestamp st = Timestamp.valueOf(startTimeStamp);
		Timestamp et = Timestamp.valueOf(endTimeStamp);
		
		reserve.setStartDate(st);
		reserve.setStartTime(st);
		reserve.setEndDate(et);
		reserve.setEndTime(et);
		
		return reserve;
	}
	
	private Reserve mySetReserve(Reserve reserve, String name, int userId) {
		int reserveId = 0;
		try {
			reserve = parsingDateTime(reserve);
			reserveId = reserveService.getMaxReserveId() + 1;
			reserve.setReserveId(reserveId);
			reserve.setCaptainName(name);
			reserve.setCaptainPhone(memberService.getPhone(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reserve;
	}
}
