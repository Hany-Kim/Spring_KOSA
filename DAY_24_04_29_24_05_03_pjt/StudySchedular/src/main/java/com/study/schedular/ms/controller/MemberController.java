package com.study.schedular.ms.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.schedular.ms.model.Member;
import com.study.schedular.ms.model.Reserve;
import com.study.schedular.ms.model.StudyHistory;
import com.study.schedular.ms.service.IMemberReserveJoinService;
import com.study.schedular.ms.service.IMemberService;
import com.study.schedular.ms.service.IReserveService;
import com.study.schedular.ms.service.IStudyService;

@Controller
public class MemberController {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	IReserveService reserveService;
	
	@Autowired
	IStudyService studyService;
	
	@Autowired
	IMemberReserveJoinService mrjService;
	
	@GetMapping("/ms/home")
	public String insertControll() {
		// 메인 화면
		return "home";
	}
	
    @GetMapping("/ms/list")
    public String getAllMembers(Model model) {
    	// 회원 목록
        List<Member> memberList = memberService.getAllMembers();
        model.addAttribute("memberList", memberList);
        return "ms/list";
    }
    
    @GetMapping("/ms/list/{name}")
    public String searchMembersByName(@RequestParam("name") String name, Model model) {
        List<Member> searchResult = memberService.searchMembersByName(name);
        model.addAttribute("memberList", searchResult);
        return "ms/list";
    }

    //우석님 작업중
    @GetMapping("/ms/{userId}")
    public String getUserInfo(@PathVariable int userId, Model model, HttpServletRequest request) {
    	// 회원 상세 조회
        try {
        	// 세션에서 userId가져오기
        	HttpSession session = request.getSession();
            String loggedInUserId = (String) session.getAttribute("userId");
            
            // 세션의 userId와 URL에서 받은 userId 비교
            if (loggedInUserId == null || !Integer.toString(userId).equals(loggedInUserId)) {
                session.setAttribute("errorMessage", "접근 권한이 없습니다.");
                return "redirect:/ms/list"; // 접근 권한이 없으므로 list로 리다이렉션
            }

        	// 기본 프로필정보
            Member member = memberService.getUserInfo(userId);
            model.addAttribute("member", member);
        	
            // 시간형식변경
            // 미래 예약한 내역 가져오기
            List<Reserve> future = memberService.getFutureReserveInfo(userId);
            List<StudyHistory> fshList = reserveToStudyhistory(future);
            //model.addAttribute("future", future);
            model.addAttribute("fshList", fshList);

            // 과거 예약한 내역 가져오기
            List<Reserve> past = memberService.getPastReserveInfo(userId);
            List<StudyHistory> pshList = reserveToStudyhistory(past);
            //model.addAttribute("past", past);
            model.addAttribute("pshList", pshList);
            
            session.removeAttribute("errorMessage");
        } catch (Exception e) {
        	e.printStackTrace();
        	return "ms/list";
        }
        return "ms/profile";
    };
    
    public List<StudyHistory> reserveToStudyhistory(List<Reserve> r) {
    	List<StudyHistory> shList = new ArrayList();
        for (Reserve f : r){
        	String start = f.getStartDate().toString();
        	String end = f.getEndDate().toString();
        	String[] splitStart = start.split(" ");
        	String[] splitEnd = end.split(" ");
        	
        	String startDate = splitStart[0];
        	String[] startDateList = startDate.split("-");
        	String startTime = splitStart[1];
        	String[] startTimeList = startTime.split(":");
        	String endDate = splitEnd[0];
        	String[] endDateList = endDate.split("-");
        	String endTime = splitEnd[1];
        	String[] endTimeList = endTime.split(":");
        	
        	String sDate = startDateList[0] + "년 "
    					+ startDateList[1] + "월 "
    					+ startDateList[2] + "일";
        	String sTime = startTimeList[0] + "시";
        	
			String eDate = endDateList[0] + "년 "
						+ endDateList[1] + "월 "
						+ endDateList[2] + "일";
        	String eTime = endTimeList[0] + "시 ";
        	
        	String studyTitleUri = studyService.getStudyUri(reserveService.getStudyId(f.getReserveId()));
        	// 010 0000 0000
        	String viewPhoneNum = f.getCaptainPhone().substring(7);
        	
        	StudyHistory sh = new StudyHistory();
        	sh.setReserveId(f.getReserveId());
        	sh.setCaptainName(f.getCaptainName());
        	sh.setCaptainPhone(viewPhoneNum);
        	sh.setStartDate(sDate);
        	sh.setStartTime(sTime);
        	sh.setEndDate(eDate);
        	sh.setEndTime(eTime);
        	sh.setStudyTitle(studyTitleUri);
        	shList.add(sh);
        }
        
        return shList;
    }

	@GetMapping("/ms/update")
	public String updateMember(int userId, Model model) {
		Member member = memberService.getUserInfo(userId);
		model.addAttribute("member", member);
		return "ms/update";
	}
	
	@PostMapping("/ms/update")
	public String updateMember(Member member, 
			@RequestParam MultipartFile file, 
			RedirectAttributes redirectAttributes, 
			HttpSession session) {
		
		if (!file.isEmpty()) {
	        // 만들고 있음.
			try {
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
				memberService.updateMember(member);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			String uploadDir = memberService.getProfile(member.getUserId());
			member.setProfile(uploadDir);
		}
		
	    
	    try {
	        memberService.updateMember(member);
	        redirectAttributes.addFlashAttribute("message", member.getName() + "의 정보가 수정되었습니다.");
	    } catch (RuntimeException e) {
	        redirectAttributes.addFlashAttribute("message", "업데이트 실패: " + e.getMessage());
	        return "redirect:/ms/update";
	    }
	    return "redirect:/ms/list";
	}
	
//	reserve로 study바꾸기
//	공부한 내용수정
	@GetMapping(value="/ms/study_detail")
	public String searchStudy(int reserveid, Model model) {
		Reserve past = memberService.onePastReserveInfo(reserveid);
		String studyTitleUri = studyService.getStudyUri(reserveService.getStudyId(past.getReserveId()));
		
		String start = past.getStartDate().toString();
    	String end = past.getEndDate().toString();
    	String[] splitStart = start.split(" ");
    	String[] splitEnd = end.split(" ");
    	
    	String startDate = splitStart[0];
    	String[] startDateList = startDate.split("-");
    	String startTime = splitStart[1];
    	String[] startTimeList = startTime.split(":");
    	String endDate = splitEnd[0];
    	String[] endDateList = endDate.split("-");
    	String endTime = splitEnd[1];
    	String[] endTimeList = endTime.split(":");
    	
    	String sDate = startDateList[0] + "년 "
					+ startDateList[1] + "월 "
					+ startDateList[2] + "일";
    	String sTime = startTimeList[0] + "시";
    	
		String eDate = endDateList[0] + "년 "
					+ endDateList[1] + "월 "
					+ endDateList[2] + "일";
    	String eTime = endTimeList[0] + "시 ";
		
		StudyHistory sh = new StudyHistory();
		sh.setReserveId(past.getReserveId());
		sh.setCaptainName(past.getCaptainName());
		sh.setCaptainPhone(past.getCaptainPhone());
		sh.setStartDate(sDate);
		sh.setStartTime(sTime);
		sh.setEndDate(eDate);
		sh.setEndTime(eTime);
		sh.setStudyTitle(studyTitleUri);
		sh.setStudyContent(past.getContent());
		sh.setStudyId(reserveService.getStudyId(past.getReserveId()));
		
		Member member = new Member();
		member.setName(sh.getCaptainName());
		member.setPhone(sh.getCaptainPhone());
		sh.setUserId(Integer.parseInt(memberService.getMemberId(member)));
		
//		model.addAttribute("past", past);
        model.addAttribute("past", sh);
		
		
		return "ms/study_detail";
	}

	@GetMapping(value="/ms/study_update")
	public String updateStudy(int reserveid, Model model) {
		Reserve post = memberService.onePastReserveInfo(reserveid);
		String studyTitleUri = studyService.getStudyUri(reserveService.getStudyId(post.getReserveId()));
		
		String start = post.getStartDate().toString();
    	String end = post.getEndDate().toString();
    	String[] splitStart = start.split(" ");
    	String[] splitEnd = end.split(" ");
    	
    	String startDate = splitStart[0];
    	String[] startDateList = startDate.split("-");
    	String startTime = splitStart[1];
    	String[] startTimeList = startTime.split(":");
    	String endDate = splitEnd[0];
    	String[] endDateList = endDate.split("-");
    	String endTime = splitEnd[1];
    	String[] endTimeList = endTime.split(":");
    	
    	String sDate = startDateList[0] + "년 "
					+ startDateList[1] + "월 "
					+ startDateList[2] + "일";
    	String sTime = startTimeList[0] + "시";
    	
		String eDate = endDateList[0] + "년 "
					+ endDateList[1] + "월 "
					+ endDateList[2] + "일";
    	String eTime = endTimeList[0] + "시 ";
    	
    	String sDateTime = splitStart[0] + "T" + splitStart[1];
    	String eDateTime = splitEnd[0] + "T" + splitEnd[1];
    	
		StudyHistory sh = new StudyHistory();
		sh.setReserveId(post.getReserveId());
		sh.setCaptainName(post.getCaptainName());
		sh.setCaptainPhone(post.getCaptainPhone());
		sh.setStudyTitle(studyTitleUri);
		sh.setStudyContent(post.getContent());
		sh.setStartDate(sDate);
		sh.setStartTime(sTime);
		sh.setEndDate(eDate);
		sh.setEndTime(eTime);
		sh.setStart(sDateTime);
		sh.setEnd(eDateTime);
		sh.setStudyTitle(studyTitleUri);
		
		
        model.addAttribute("past", sh);
        model.addAttribute("studyList", studyService.getAllStudies());
		return "ms/study_update";
	}
	
	@PostMapping(value="/ms/study_update")
	public String updateStudy(StudyHistory sh,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		// 예약 정보 수정
		Reserve reserve = new Reserve();
		reserve.setReserveId(sh.getReserveId());
		reserve.setContent(sh.getStudyContent());
		reserve.setStudyId(sh.getStudyId());
		reserve.setCaptainName(sh.getCaptainName());
		reserve.setCaptainPhone(sh.getCaptainPhone());
		String sDateTime = parsingDateTime2(sh.getStart());
		String eDateTime = parsingDateTime2(sh.getEnd());
		
		System.out.println("sDateTime : " + sDateTime);
		System.out.println("eDateTime : " + eDateTime);
		
		reserve.setStartDate(Timestamp.valueOf(sDateTime));
		reserve.setStartTime(Timestamp.valueOf(sDateTime));
		reserve.setEndDate(Timestamp.valueOf(eDateTime));
		reserve.setEndTime(Timestamp.valueOf(eDateTime));
		
		reserveService.updateReserve(reserve);
		
		return "redirect: /ms/study_detail?reserveid=" + reserve.getReserveId();
	}
	
	private String parsingDateTime2(String time) {
		String[] mDateTime = time.split("T");
		
		String mDate = mDateTime[0];
		String mTime = mDateTime[1];
		
		String mTimeStamp = mDate + " " + mTime;
		
		return mTimeStamp;
	}
	
	@GetMapping(value="/ms/reserve_detail")
	public String searchReserve(int reserveid, Model model) {
		// 예약 내역 상세 조회
		Reserve future = memberService.onePastReserveInfo(reserveid);
		String studyTitleUri = studyService.getStudyUri(reserveService.getStudyId(future.getReserveId()));
		
		String start = future.getStartDate().toString();
    	String end = future.getEndDate().toString();
    	String[] splitStart = start.split(" ");
    	String[] splitEnd = end.split(" ");
    	
    	String startDate = splitStart[0];
    	String[] startDateList = startDate.split("-");
    	String startTime = splitStart[1];
    	String[] startTimeList = startTime.split(":");
    	String endDate = splitEnd[0];
    	String[] endDateList = endDate.split("-");
    	String endTime = splitEnd[1];
    	String[] endTimeList = endTime.split(":");
    	
    	String sDate = startDateList[0] + "년 "
					+ startDateList[1] + "월 "
					+ startDateList[2] + "일";
    	String sTime = startTimeList[0] + "시";
    	
		String eDate = endDateList[0] + "년 "
					+ endDateList[1] + "월 "
					+ endDateList[2] + "일";
    	String eTime = endTimeList[0] + "시 ";
		
		StudyHistory sh = new StudyHistory();
		sh.setReserveId(future.getReserveId());
		sh.setCaptainName(future.getCaptainName());
		sh.setCaptainPhone(future.getCaptainPhone());
		sh.setStartDate(sDate);
		sh.setStartTime(sTime);
		sh.setEndDate(eDate);
		sh.setEndTime(eTime);
		sh.setStudyTitle(studyTitleUri);
		sh.setStudyContent(future.getContent());
		sh.setStudyId(reserveService.getStudyId(future.getReserveId()));
		
		Member member = new Member();
		member.setName(sh.getCaptainName());
		member.setPhone(sh.getCaptainPhone());
		sh.setUserId(Integer.parseInt(memberService.getMemberId(member)));
		
        //model.addAttribute("future", future);
        model.addAttribute("future", sh);
		return "ms/reserve_detail";
	}
	
	
	@GetMapping(value="/ms/reserve_update")
	public String updateReserve(int reserveid, Model model) {
		Reserve future = memberService.onePastReserveInfo(reserveid);
		String studyTitleUri = studyService.getStudyUri(reserveService.getStudyId(future.getReserveId()));
		
		String start = future.getStartDate().toString();
    	String end = future.getEndDate().toString();
    	String[] splitStart = start.split(" ");
    	String[] splitEnd = end.split(" ");
    	
    	String sDate = splitStart[0] + "T" + splitStart[1];
    	String eDate = splitEnd[0] + "T" + splitEnd[1];
    	/*
    	수정 sh : StudyHistory(
    	reserveId=10, 
    	captainName=김주한, captainPhone=01012121212, 
    	startDate=null, startTime=null, 
    	endDate=null, endTime=null, 
    	studyTitle=null, studyContent=계획된 내용이 없습니다., 
    	start=2024-05-25T00:12, end=2024-05-28T12:12)
    	수정 reserve : Reserve(reserveId=10, startTime=null, endTime=null, startDate=null, endDate=null, content=null, studyId=1, captainName=김주한, captainPhone=01012121212, start=2024-05-25T00:12, end=2024-05-28T12:12, studyName=null)
    	*/
		StudyHistory sh = new StudyHistory();
		sh.setReserveId(future.getReserveId());
		sh.setCaptainName(future.getCaptainName());
		sh.setCaptainPhone(future.getCaptainPhone());
		sh.setStudyTitle(studyTitleUri);
		sh.setStudyContent(future.getContent());
		sh.setStart(sDate);
		sh.setEnd(eDate);
		
		
        model.addAttribute("future", sh);
        model.addAttribute("studyList", studyService.getAllStudies());
		return "ms/reserve_update";
	}
//	
	@PostMapping(value="/ms/reserve_update")
	public String updateReserve(StudyHistory sh,
								RedirectAttributes redirectAttributes,
								HttpSession session) {
		// 예약 정보 수정
		Reserve reserve = new Reserve();
		reserve.setReserveId(sh.getReserveId());
		reserve.setContent(sh.getStudyContent());
		reserve.setStudyId(sh.getStudyId());
		reserve.setCaptainName(sh.getCaptainName());
		reserve.setCaptainPhone(sh.getCaptainPhone());
		//reserve = parsingDateTime(reserve);
		String sDateTime = parsingDateTime(sh.getStart());
		String eDateTime = parsingDateTime(sh.getEnd());
		reserve.setStartDate(Timestamp.valueOf(sDateTime));
		reserve.setStartTime(Timestamp.valueOf(sDateTime));
		reserve.setEndDate(Timestamp.valueOf(eDateTime));
		reserve.setEndTime(Timestamp.valueOf(eDateTime));
		
		reserveService.updateReserve(reserve);
		
		return "redirect: /ms/reserve_detail?reserveid=" + reserve.getReserveId();
	}
	
	private String parsingDateTime(String time) {
		String[] mDateTime = time.split("T");
		
		String mDate = mDateTime[0];
		String mTime = mDateTime[1];
		mTime = mTime + ":00.0";
		
		String mTimeStamp = mDate + " " + mTime;
		
		return mTimeStamp;
	}
	
	@PostMapping(value="/ms/delete")
	public String deleteReserve(int userId, int reserveId, StudyHistory sh,
			HttpSession session,
			RedirectAttributes redAttr, Model model) {
		userId = Integer.parseInt(session.getAttribute("userId").toString());
		try {
			int deletedJoin = mrjService.deleteJoin(userId, reserveId);
			int deleteReserve = reserveService.deleteReserve(reserveId);
			return "redirect:/ms/" + userId;
		} catch (RuntimeException e) {
			System.out.println("catch");
			return "redirect:/ms/" + userId;
		}
	}
}