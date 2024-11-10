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
@Controller
public class FarmController {
	
	private final CommonUtility common;
	
	private String  Key = "20241025JY4SUEANOT7KEEDB4U8A";
	private String videoListURL = "http://api.nongsaro.go.kr/service/agriTechVideo/videoList";
	
	
	//농업기술동영상 리스트 데이터 요청
		@ResponseBody @RequestMapping("/videoList")
		public Object videoList(String mainCategoryList) {
			//농사로에서 농업기술동영상 리스트 조회
			StringBuffer url = new StringBuffer(videoListURL);
			url.append("?apiKey=").append(Key)
			.append("&_type=json")
			;
			
			return XML.toJSONObject(common.requestAPI(url.toString())).getJSONObject("response").getJSONObject("body").toMap();
		}
	
	@RequestMapping("/")
	public String home(HttpSession session, Model model, PageVO page) {
		session.setAttribute("category", "home");
		page.setListSize(6);

		//농사로에서 농업기술동영상 리스트 조회
		StringBuffer url = new StringBuffer(videoListURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json")
		   .append("&pageNo=").append(page.getPageNo())
		   .append("&numOfRows=").append(page.getListSize())
		;
		
		// API 요청 후 데이터 매핑
		Map<String, Object> videoList = XML.toJSONObject(common.requestAPI(url.toString()))
										  .getJSONObject("response")
										  .getJSONObject("body")
										  .toMap();
		model.addAttribute("videoList", videoList);
		
		return "index";
	}
}
