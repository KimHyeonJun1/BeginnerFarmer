package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.log.LogMapper;
import kr.co.farm.log.LogVO;
import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.RequiredArgsConstructor;

@Controller @RequestMapping("/log") @RequiredArgsConstructor
public class LogController {
	private final ManageMapper manage;
	private final LogMapper mapper;
	
	
	
	
	
	
	@RequestMapping("/water_management")
	public String LogWaterManagement(HttpSession session) {
		session.setAttribute("category", "wa");
		return "log/water_management";
	}
	
	@RequestMapping("/temperature")
	public String LogTemperature(HttpSession session) {
		session.setAttribute("category", "te");
		return "log/temperature";
	}
	
	@RequestMapping("/monitor") //실시간 모니터링 화면 요청
	public String LogMonitor(Authentication user, ManageVO vo, LogVO log,HttpSession session, Model model) {
		String userid_log = user.getName();
		List<ManageVO> plant = manage.getUserPlants(userid_log);
		model.addAttribute("plant", plant);
//		mapper.getCountDate();
		session.setAttribute("category", "mo");
		return "log/monitor";
	}
}
