package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.myapp.hr.service.IEmpService;

@Controller
public class HomeController {
	
	@Autowired
	IEmpService empService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "Hello~~~~~~~~~");
		model.addAttribute("count", empService.getEmpCount());
		model.addAttribute("emp", empService.getEmpInfo(100));
		return "/home";
	}
}
