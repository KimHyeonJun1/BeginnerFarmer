package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.guide.GuideMapper;
import kr.co.farm.guide.GuideVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller	@RequestMapping("/guide")
public class GuideController {
	
	private GuideMapper mapper;
	
	// 작물가이드 정보화면 요청
	@RequestMapping("/info")
	public String info(int plant_id, Model model) {
		// 선택한 작물을 DB에서 조회해오기 -> 화면에 출력할 수 있게 Model 객체에 저장하기
		GuideVO vo = mapper.getOneGuide(plant_id);
		model.addAttribute("vo", vo);
		
		return "guide/info";
	}
	
	// 작물가이드 목록화면 요청
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category", "gu");
		// DB에서 작물가이드 목록 조회해오기 -> 화면에 출력할 수 있게 Model 객체에 저장하기
		List<GuideVO> list = mapper.getListOfGuide();
		model.addAttribute("list", list);
		
		return "guide/list";
	}
	
	
}
