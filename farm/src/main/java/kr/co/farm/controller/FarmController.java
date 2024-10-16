package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FarmController {
	@RequestMapping("/")
	public String home(HttpSession session) {
		session.removeAttribute("category");
		return "index";
	}
	
}
