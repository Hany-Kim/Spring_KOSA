package com.example.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.hr.model.Emp;
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
			@RequestParam(value="depid", required=false, 
			defaultValue="0") int deptid, Model model) {
		int result = 0;
		if(deptid==0) {
			result = empService.getEmpCount(); // 모든 사원의 수
		} else {
			result = empService.getEmpCount(deptid); // 부서의 사원의 수
		}
		model.addAttribute("count", result);
		return "hr/count"; //WEB-INF/views/hr/count.jsp
	}
	
	@GetMapping("/hr/insert")
	public String insertEmp(Model model) {
		System.out.println("/hr/insert 핸들러 실행");
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		return "hr/insertform";
	}
	
	@PostMapping("/hr/insert")
	public String insertEmp(Emp emp, RedirectAttributes model) {
		try {
			empService.insertEmp(emp);
			model.addFlashAttribute("message", emp.getFirstName() + "사원 정보가 저장되었습니다.");
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
			model.addFlashAttribute("message", e.getMessage());
		}
//		return "redirect:count"; // redirect : 저장을하고 해당 url로 다시 이동해줘
		return "redirect:/hr/list"; // redirect : 저장을하고 해당 url로 다시 이동해줘
	}
	
	@GetMapping("/hr/{employeeId}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		Emp emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		return "hr/view";
	}
	
	@GetMapping("/hr/list")
	public String getAllEmps(Model model) {
		List<Emp> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}
	
	@GetMapping("/hr/update")
	public String updateEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/updateform";
	}
	
	@PostMapping(value="/hr/update")
	public String updateEmp(Emp emp, RedirectAttributes redirectAttributes) {
		try {
			empService.updateEmp(emp);
			redirectAttributes.addFlashAttribute("message", 
					emp.getEmployeeId() + "번 사원정보가 수정되었습니다.");
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/hr/" + emp.getEmployeeId();
	}
	
	@GetMapping(value="/hr/delete")
	public String deleteEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		return "hr/deleteform";
	}
	
	@PostMapping(value="/hr/delete")
	public String deleteEmp(int empid, String email, 
			RedirectAttributes redAttr, Model model) {
		try {
			int deletedRow = empService.deleteEmp(empid, email);
			if(deletedRow > 0) {
				redAttr.addFlashAttribute("message", empid + "번 사원정보가 삭제되었습니다.");
				return "redirect:/hr/list";
			} else {
				model.addAttribute("message", "ID 또는 Email이 다릅니다.");
				model.addAttribute("emp", empService.getEmpInfo(empid));
				return "hr/deleteform";
			}
		} catch (RuntimeException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("emp", empService.getEmpInfo(empid));
			return "redirect:/hr/list";
		}
	}
	
	@ExceptionHandler({RuntimeException.class})
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
}
