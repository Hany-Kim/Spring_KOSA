package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/")
	public @ResponseBody String index() {
		return "welcome home";
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name, Model model) {
		model.addAttribute("greetings", "Hello~~" + name);
		return "hello";
	}
	
	
}
