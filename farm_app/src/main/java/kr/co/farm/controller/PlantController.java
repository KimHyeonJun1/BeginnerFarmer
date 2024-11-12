package kr.co.farm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.plant.PlantMapper;
import kr.co.farm.plant.PlantVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/plant")
@RestController
public class PlantController {
	private final PlantMapper mapper;
	
	//온/습도
	@RequestMapping("/data")
	public String data (String userid, String plantid) {
		PlantVO vo = mapper.getOneData(userid, plantid);
		return new Gson().toJson(vo);
	}
	
	//조명 제어
//	@RequestMapping("/right")
//	public String right () {
//		return new Gson().toJson(vo);
//	}
	
	//물주기
	@RequestMapping("/moisture")
	public String mositure (String userid, String plantid) {
		PlantVO vo = mapper.getOnePlant(userid, plantid);
		return new Gson().toJson(vo);
	}
	
}
