package com.study.schedular.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.study.schedular.ms.common.except.NotFoundException;
import com.study.schedular.ms.model.Study;
import com.study.schedular.ms.service.IStudyService;

@Controller
public class StudyController {

	@Autowired
	IStudyService studyService;
	
    @GetMapping("/study/list")
    public String getAllMembers(Model model) {
        List<Study> studyList = studyService.getAllStudies();
        model.addAttribute("studyList", studyList);
        return "study/study_list";
    }
    
    @GetMapping("/study/search")
    public String searchMembersByName(@RequestParam("study_name") String studyName, Model model) {
        List<Study> studySearchResult = studyService.searchStudiesByName(studyName);
        model.addAttribute("studyList", studySearchResult);
        return "study/study_list";
    }
    
    @GetMapping("/study/{studyId}")
	public String getStudyInfo(@PathVariable int studyId, Model model) {
		try {
			Study study = studyService.getStudyInfo(studyId);
			model.addAttribute("study", study);
		}catch(EmptyResultDataAccessException e) {
			throw new NotFoundException("해당 멤버가 없습니다.");
		}
		return "study/study_view";
	}
}