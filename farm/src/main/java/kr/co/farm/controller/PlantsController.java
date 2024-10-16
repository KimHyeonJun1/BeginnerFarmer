package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	@RequestMapping("/plants")
public class PlantsController {
	
	@RequestMapping("/list")
	public String list(HttpSession session) {
		session.setAttribute("category", "pl");
		
		return "plants/list";
	}
	
	
}
