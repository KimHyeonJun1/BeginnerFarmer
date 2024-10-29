package kr.co.farm.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.common.CommonUtility;
import kr.co.farm.common.PageVO;
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
		
		return XML.toJSONObject(common.requestAPI(url.toString())).getJSONObject("response").getJSONObject("body").toMap();
	}
	
	// 주간농사정보 화면 요청
	@RequestMapping("/list")
	public String weekInfoList(HttpSession session, Model model, PageVO page) {
		session.setAttribute("category", "in");
		// 농사로에서 주간농사정보 조회해오기
		StringBuffer url = new StringBuffer(weekInfoURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json")
		   .append("&pageNo=").append(page.getPageNo())
		   .append("&numOfRows=").append(page.getListSize())
		   ;
		
		Map<String, Object> weekInfo = XML.toJSONObject(common.requestAPI(url.toString()))
										  .getJSONObject("response")
										  .getJSONObject("body")
										  .toMap();
		model.addAttribute("weekInfo", weekInfo);
		
		Map<String, Object> items = (Map<String, Object>) weekInfo.get("items");
		Integer totalCount = (Integer) items.get("totalCount");
		
		page.setTotalList(totalCount);
	    model.addAttribute("page", page);
	    
		return "weekInfo/list";
	}
}
