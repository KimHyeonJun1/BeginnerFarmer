package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.common.CommonUtility;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/weekInfo")
public class WeekInfoController {
	
	private final CommonUtility common;
	
	private String Key = "202410251EJWV6MJGPLBFEQVAHDVRQ";
	private String weekInfoURL = "http://api.nongsaro.go.kr/service/weekFarmInfo/weekFarmInfoList";

	// 주간농사정보 화면 요청 - 데이터 요청
	@ResponseBody @RequestMapping("/weekInfo")
	public Object weekInfo() {
		// 농사로에서 주간농사정보 조회해오기
		StringBuffer url = new StringBuffer(weekInfoURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json");
		
//		model.addAttribute( "list", common.responseBody( url.toString() ).toMap() );
//		JSONObject json = common.responseBody(url.toString());
		
		return XML.toJSONObject(common.requestAPI(url.toString())).getJSONObject("response").getJSONObject("body").toMap();
	}
	
	// 주간농사정보 화면 요청
	@RequestMapping("/list")
	public String weekInfoList(HttpSession session, Model model) {
//	public String weekInfoList(@RequestBody HashMap<String, Object> map, HttpSession session, Model model) {
		session.setAttribute("category", "in");
		// 농사로에서 주간농사정보 조회해오기
		StringBuffer url = new StringBuffer(weekInfoURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json");
		
//		return common.requestAPI(url.toString());
		
		
//		JSONObject json = common.response(url.toString());
//		model.addAttribute("weekInfo", json.toMap());
//		
		// 오류가 있다면 오류내용을 출력할 수 있게하기
//		model.addAttribute("response", json.getJSONObject("header").toMap());
//		
//		json = json.getJSONObject("body");
//		
		return "weekInfo/list";
	}
}
