package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	@RequestMapping("/monitor")
public class MonitorController {
	
		
		@RequestMapping("/list")
		public String list(HttpSession session) {
			session.setAttribute("category", "mo");
			
			return "monitor/list";
		}
		
		
	}