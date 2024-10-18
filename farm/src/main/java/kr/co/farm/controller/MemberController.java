package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.member.MemberVO;

@Controller @RequestMapping("/member")
public class MemberController {

	//로그인 화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
//		session.setAttribute("", "login");
		MemberVO vo = null;
		if(vo == null) {
			
		}
		
		return "default/member/login";
	}
}
