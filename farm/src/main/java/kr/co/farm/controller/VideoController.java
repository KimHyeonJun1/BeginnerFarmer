package kr.co.farm.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.common.CommonUtility;
import kr.co.farm.common.PageVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/video")
public class VideoController {
	
	private final CommonUtility common;
	
	private String  Key = "20241025JY4SUEANOT7KEEDB4U8A";
	private String videoListURL = "http://api.nongsaro.go.kr/service/agriTechVideo/videoList";
	private String mainCategoryListURL = "http://api.nongsaro.go.kr/service/agriTechVideo/mainCategoryList";
	private String subCategoryListURL = "http://api.nongsaro.go.kr/service/agriTechVideo/subCategoryList";
	
	
	//메인카테고리 화면 요청
	@RequestMapping("/mainCg")
	public String main ( Model model,  HttpSession session) {
		session.setAttribute("category", "vi");
		
		StringBuffer url = new StringBuffer(mainCategoryListURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json")
		   ;
		
		// API 요청 후 데이터 매핑
		Map<String, Object> mainCategoryList = XML.toJSONObject(common.requestAPI(url.toString()))
										  .getJSONObject("response")
										  .getJSONObject("body")
										  .toMap();
		model.addAttribute("mainCategoryList", mainCategoryList);
		
		
		return "video/category/mainCategoryList";
	}
	
	//메인카테고리 데이터 요청
	@ResponseBody @RequestMapping("/mainCategoryList")
	public Object mainCategoryList() {
		//농사로에서 농업기술동영상 리스트 조회
		StringBuffer url = new StringBuffer(mainCategoryListURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json");
		
		return XML.toJSONObject(common.requestAPI(url.toString())).getJSONObject("response").getJSONObject("body").toMap();
	}
	
	
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
	
	//농업기술동영상 리스트 화면 요청
	@RequestMapping("/list")
	public String list ( HttpSession session, Model model, PageVO page, String mainCategory, String subCategory) {
		session.setAttribute("category", "vi");
		page.setListSize(12);
		//농사로에서 농업기술동영상 리스트 조회
		String main = mainCategory == null ? "":mainCategory;
		String sub = subCategory == null ? "": subCategory;
		
		StringBuffer url = new StringBuffer(videoListURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json")
		   .append("&pageNo=").append(page.getPageNo())
		   .append("&numOfRows=").append(page.getListSize())
		   .append("&categoryCode=").append(sub.isEmpty() ? main:sub)
		   ;
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("subCategory", subCategory);
		
		// API 요청 후 데이터 매핑
		JSONObject body = XML.toJSONObject(common.requestAPI(url.toString()))
				.getJSONObject("response")
				.getJSONObject("body");
		
		JSONObject json = body.getJSONObject("items");
		if(  json.get("item")  instanceof JSONObject ) {
			json.put("item", new JSONArray().put(0, json.get("item")));
		}
		
		Map<String, Object> videoList = body.toMap();
		model.addAttribute("videoList", videoList);

		// 페이지 총 수 설정
		int totalCount =json.getInt("totalCount");
		page.setTotalList(totalCount);
	    model.addAttribute("page", page);
	    
	    url = new StringBuffer(mainCategoryListURL);
		url.append("?apiKey=").append(Key)
		   .append("&_type=json")
		   ;
		
		// API 요청 후 데이터 매핑
		Map<String, Object> mainCategoryList = XML.toJSONObject(common.requestAPI(url.toString()))
										  .getJSONObject("response")
										  .getJSONObject("body")
										  .toMap();
		model.addAttribute("mainCategoryList", mainCategoryList);
		
		if(! main.isEmpty()) {
			 url = new StringBuffer(subCategoryListURL);
				url.append("?apiKey=").append(Key)
				   .append("&_type=json")
				   .append("&categoryCode=").append(main)
				   ;
				
				// API 요청 후 데이터 매핑
				Map<String, Object> subCategoryList = XML.toJSONObject(common.requestAPI(url.toString()))
												  .getJSONObject("response")
												  .getJSONObject("body")
												  .toMap();
				model.addAttribute("subCategoryList", subCategoryList);
			
		}
		
		return "video/list";
	}
	
	
	
}
