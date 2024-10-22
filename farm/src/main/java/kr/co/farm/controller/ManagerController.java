package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	@RequestMapping("/manager")
public class ManagerController {
	
	
	
	
	
	@RequestMapping("/register")
	public String register() {
		return "manager/register";
	}
	
	
	@RequestMapping("/list")
	public String list(HttpSession session) {
		session.setAttribute("category", "ma");
		
		return "manager/list";
	}
	
	
}
