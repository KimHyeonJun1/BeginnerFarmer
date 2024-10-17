package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/member")
public class MemberController {

	//로그인 화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
//		session.setAttribute("", "login");
		return "default/member/login";
	}
}
