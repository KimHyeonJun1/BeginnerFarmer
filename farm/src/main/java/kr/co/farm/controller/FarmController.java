package kr.co.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FarmController {
	@RequestMapping("/")
	public String home() {
		// 브랜치 테스트
		// 브랜치 테스트2222
		// 브랜치 테스트3333
		// 브랜치 테스트4444
		// 브랜치 테스트5555
		// 브랜치 테스트6666
		return "index";
	}
	
}
