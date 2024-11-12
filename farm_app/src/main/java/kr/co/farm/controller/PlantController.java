package kr.co.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.plant.PlantMapper;
import kr.co.farm.plant.PlantVO;
import kr.co.farm.plant.WaterVO;
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
	
//	@RequestMapping("/moisture")
//	public String mositure (String userid, String plantid) {
//		PlantVO vo = mapper.getOnePlant(userid, plantid);
//		
//		return new Gson().toJson(vo);
//	}
	
	//급수관리
	@RequestMapping("/moisture")
	public String moisture(String userid, String plantid) {
	    PlantVO plantVo = mapper.getOnePlant(userid, plantid); // Moisture 정보
	    List<WaterVO> waterList = mapper.getListOfWaterByUser(userid, plantid); // 급수 관리 리스트
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("plantData", plantVo); // PlantVO 데이터
	    response.put("waterList", waterList); // 급수 관리 데이터 리스트

	    return new Gson().toJson(response);
	}

	
}
