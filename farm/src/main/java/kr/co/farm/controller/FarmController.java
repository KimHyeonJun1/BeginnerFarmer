package kr.co.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FarmController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
}
