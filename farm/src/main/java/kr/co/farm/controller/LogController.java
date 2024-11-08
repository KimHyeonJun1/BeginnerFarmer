package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.common.PageVO;
import kr.co.farm.guide.GuideVO;
import kr.co.farm.log.LogMapper;
import kr.co.farm.log.TemperatureVO;
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
	public String LogWaterManagement(Authentication user, HttpSession session, Model model,  PageVO page) {
		if(user == null) {
			return "redirect:/manage/list";
		}
		String userid_log = user.getName();
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");
		if (plantid_log  == null) {
			return "redirect:/manage/list"; // 선택된 plantid_log가 없으면 info 페이지로 리다이렉트
		}
		
	    // 총 목록 수를 카운트하여 PageVO에 설정
	    int totalWaterCount = mapper.countOfWater( userid_log, page, plantid_log);
	    page.setTotalList(totalWaterCount);
		
		//페이지 처리
		List<Object> waterList = mapper.getListOfWaterByUser( userid_log, page, plantid_log);
		page.setList(waterList);
		
		
		model.addAttribute("waterList", waterList);
		model.addAttribute("page", page);
		
		//사용자의 등록된 작물목록 조회
		List<ManageVO> plant = manageMapper.getUserPlants(userid_log); 
		model.addAttribute("plant", plant); 
		
		//온/습/조도 정보 조회
		TemperatureVO temp = mapper.getOneTemperature(userid_log, plantid_log);
		model.addAttribute("temp", temp);
		
		ManageVO selectedPlant = manageMapper.getPlantInfo(userid_log, plantid_log); 
		model.addAttribute("vo", selectedPlant);
		
		session.setAttribute("category", "wa");
		return "log/water_management";
	}
	
	
	
	// 온도/습도/조도 화면
	@RequestMapping("/temperature")
	public String LogTemperature(Authentication user, Model model, HttpSession session) {
		if(user == null) {
			return "redirect:/manage/list";
		}
		String userid_log = user.getName();	
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");
		if (plantid_log  == null) {
			return "redirect:/manage/list"; // 선택된 plantid_log가 없으면 info 페이지로 리다이렉트
		}
		
		//사용자의 등록된 작물목록 조회
		List<ManageVO> plant = manageMapper.getUserPlants(userid_log); 
		model.addAttribute("plant", plant); 
		
		
		//온/습/조도 정보 조회
		TemperatureVO temp = mapper.getOneTemperature(userid_log, plantid_log);
		model.addAttribute("temp", temp);
		
		//플랜테이블 정보 조회
		GuideVO standardInfo = mapper.getPlantStandardInfo(plantid_log);
		model.addAttribute("vo", standardInfo);
		if(temp != null) {
			String message = "";
			
			// 온도와 습도 상태 메시지 생성
			if (temp.getTemperature() > standardInfo.getStandard_temp()) {
				message += "적정온도 보다 높습니다." + "<br>";
			} else if (temp.getTemperature() < standardInfo.getStandard_temp()) {
				message += "적정온도 보다 낮습니다."  + "<br>";
			} else {
				message += "적정온도입니다."  + "<br>";
			}
			
			// 습도 상태 메시지 생성
			if (temp.getHumid() > standardInfo.getStandard_hum()) {
				message += "적정습도 보다 높습니다.";
			} else if (temp.getHumid() < standardInfo.getStandard_hum()) {
				message += "적정습도 보다 낮습니다.";
			} else {
				message += "적정습도입니다.";
			}
			
			// 결과 메시지를 모델에 추가
			model.addAttribute("conditionMessage", message.trim());
		
		
		
		}
		session.setAttribute("category", "te");
		return "log/temperature";
	}
	//온도 조회 요청
	@ResponseBody
	@GetMapping("/temp")
	public Object temp(Authentication user, HttpSession session) {
		String userid_log = user.getName();	
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");
		return mapper.getCountByTemperature(userid_log, plantid_log);
	}
	
	
	
	
	//실시간 모니터링 화면 요청
	@RequestMapping("/monitor") 
	public String LogMonitor(Authentication user,  HttpSession session, Model model) {
		if(user == null) {
			return "redirect:/manage/list";
		}
		
		String userid_log = user.getName();	
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");  // 세션에서 선택된 plantid_log 가져오기
		
		if (plantid_log  == null) {
			return "redirect:/manage/list"; // 선택된 plantid_log가 없으면 info 페이지로 리다이렉트
		}else{
			
				//사용자의 등록된 작물목록 조회
		 List<ManageVO> plant = manageMapper.getUserPlants(userid_log); 
		  model.addAttribute("plant", plant); 	
			
		ManageVO selectedPlant = manageMapper.getPlantInfo(userid_log, plantid_log); 
	    model.addAttribute("vo", selectedPlant);
		session.setAttribute("category", "mo");		
		return "log/monitor";
				}	
	}
}
