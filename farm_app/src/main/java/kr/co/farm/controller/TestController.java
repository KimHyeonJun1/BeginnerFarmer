package kr.co.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	
	@RequestMapping("/cctv")
	public String cctv(Model model) {
		
		// mac_address를 데이터베이스에서 가져옴
//		String response = requestAPI( "http://192.168.0.34:88/stream" );
//		model.addAttribute("response", response);
		
		return "cctv";
	}
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}
