package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.common.CommonUtility;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/weekInfo")
public class WeekInfoController {
	
	private final CommonUtility common;
	
	private String apiKey = "202410251EJWV6MJGPLBFEQVAHDVRQ";
	private String weekInfoURL = "http://api.nongsaro.go.kr/service/weekFarmInfo/weekFarmInfoList";

	// 주간농사정보 화면 요청 - 데이터 요청
	public Object weekInfo() {
		// 농사로에서 주간농사정보 조회해오기
		StringBuffer url = new StringBuffer(weekInfoURL);
		url.append("?ServiceKey=").append(apiKey)
		   .append("&_type=json");
		JSONObject json = common.responseBody(url.toString());
		
		return json.toMap();
	}
	
	// 주간농사정보 화면 요청
	@RequestMapping("/list")
	public Object list(HttpSession session, Model model) {
		session.setAttribute("category", "in");
		// 농사로에서 주간농사정보 조회해오기
		StringBuffer url = new StringBuffer(weekInfoURL);
		url.append("?ServiceKey=").append(apiKey)
		   .append("&_type=json");
		
		JSONObject json = common.responseBody(url.toString());
		model.addAttribute("weekInfo", json.toMap());
		
		return "weekInfo/list";
	}
}
