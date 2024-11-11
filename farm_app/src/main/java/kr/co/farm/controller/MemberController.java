package kr.co.farm.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor	@RequestMapping("/member")
public class MemberController {
	
	private final MemberMapper mapper;
	private final PasswordEncoder password;
	
	@ResponseBody @RequestMapping("/login")
	public String login(String userid, String userpw) {
		MemberVO vo = mapper.getOneMember(userid);
		if(vo != null) {
			vo = password.matches(userpw, vo.getUserpw() ) ? vo:null ;
		}
		return new Gson().toJson(vo);
//		if(vo != null) {
//			vo = password.matches(userpw, vo.getUserpw() ) ? vo:null ;
//		}
//		return new Gson().toJson(vo);
		

	}
	
	@ResponseBody @RequestMapping("/update")
	public String update(String vo) {
		MemberVO member = new Gson().fromJson(vo, MemberVO.class);
		mapper.updateMember(member);
		member = mapper.getOneMember(member.getUserid());
		return new Gson().toJson(member);
	}
	
}
