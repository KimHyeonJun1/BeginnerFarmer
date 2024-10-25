package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/diary")
public class DiaryController {
	
	//관찰일지 글쓰기 화면 요청
	@GetMapping("/register")
	public String register() {
		return "diary/register";
	}
	
	//관찰일지 목록 화면 요청
	@RequestMapping("/list")
	public String list(HttpSession session) {
		session.setAttribute("category", "di");
		return "diary/list";
	}
}
