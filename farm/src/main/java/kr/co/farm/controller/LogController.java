package kr.co.farm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/log")
public class LogController {
	
	
	@RequestMapping("/water_management")
	public String LogWaterManagement(HttpSession session) {
		session.setAttribute("category", "ma");
		return "log/water_management";
	}
	
	@RequestMapping("/temperature")
	public String LogTemperature(HttpSession session) {
		session.setAttribute("category", "ma");
		return "log/temperature";
	}
	
	@RequestMapping("/monitor")
	public String LogMonitor(HttpSession session) {
		session.setAttribute("category", "ma");
		return "log/monitor";
	}
}
