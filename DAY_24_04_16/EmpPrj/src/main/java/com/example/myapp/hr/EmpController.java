package com.example.myapp.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	public void printInfo() {
		int count = empService.getEmpCount(50);
		System.out.println("사원의 수 : " + count);
	}
	
}
