package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.log.LogMapper;
import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.RequiredArgsConstructor;

@Controller @RequestMapping("/log") @RequiredArgsConstructor
public class LogController {

	private final ManageMapper manageMapper;
	private final LogMapper mapper;
	
	@PostMapping("/saveSelectedPlant")
	@ResponseBody
	public void saveSelectedPlant(@RequestParam("plantid_log") int plantid_log, HttpSession session) {
		session.setAttribute("plantid_log", plantid_log);
		
	}
	
	@RequestMapping("/water_management")
	public String LogWaterManagement(Authentication user, HttpSession session, Model model) {
		String userid_log = user.getName();
		
		session.setAttribute("category", "wa");
		return "log/water_management";
	}
	
	@RequestMapping("/temperature")
	public String LogTemperature(Authentication user, HttpSession session) {
		String userid_log = user.getName();	
		int plantid_log = (Integer) session.getAttribute("plantid_log");  // 세션에서 선택된 plantid_log 가져오기
		session.setAttribute("category", "te");
		return "log/temperature";
	}
	
	@GetMapping("/monitor") //실시간 모니터링 화면 요청
	public String LogMonitor(Authentication user,  HttpSession session, Model model) {
		String userid_log = user.getName();	
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");  // 세션에서 선택된 plantid_log 가져오기
		if (plantid_log  == null) {
			return "redirect:/manage/list"; // 선택된 plantid_log가 없으면 info 페이지로 리다이렉트
		}else {
		
		ManageVO selectedPlant = manageMapper.getPlantInfo(userid_log, plantid_log); 
	    model.addAttribute("vo", selectedPlant);
		session.setAttribute("category", "mo");		
		return "log/monitor";
				}	
	}
}
