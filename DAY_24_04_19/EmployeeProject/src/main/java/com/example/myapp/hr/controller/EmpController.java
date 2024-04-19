package com.example.myapp.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myapp.hr.service.IEmpService;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;
	
	// http://localhost:8080/myapp/hr/count
//	@RequestMapping("/hr/count")
	@GetMapping({"/hr/count", "/hr/cnt"})
//	public String empCount(Model model) {
//	public String empCount(int deptid, Model model) {
	public String empCount(
			@RequestParam(value="depid", required=false, defaultValue="0") int deptid, 
			Model model) {
		int result = 0;
		if(deptid==0) {
			result = empService.getEmpCount(); // 모든 사원의 수
		} else {
			result = empService.getEmpCount(deptid); // 부서의 사원의 수
		}
		model.addAttribute("count", result);
		return "hr/count"; //WEB-INF/views/hr/count.jsp
	}
}
