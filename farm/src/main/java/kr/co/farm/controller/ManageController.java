package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	@RequestMapping("/manage")
public class ManageController {
	
	
	@RequestMapping("/info")
	public String info() {
		return "manage/info";
	}
	
	
	@RequestMapping("/register")
	public String register(Model model) {
		
		
		return "manage/register";
	}
	
	
	@RequestMapping("/list")
	public String list(HttpSession session) {
		session.setAttribute("category", "ma");
		
		return "manage/list";
	}
	
	
}
