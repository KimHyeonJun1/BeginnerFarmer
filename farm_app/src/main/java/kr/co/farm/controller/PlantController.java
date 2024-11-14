package kr.co.farm.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import kr.co.farm.plant.GuideVO;
import kr.co.farm.plant.PlantMapper;
import kr.co.farm.plant.PlantVO;
import kr.co.farm.plant.WaterVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/plant")
@RestController
public class PlantController {
	private final PlantMapper mapper;
	private final MemberMapper Membermapper;
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
	
	//온/습도
	@RequestMapping("/data")
	public String data (String userid, String plantid) {
		PlantVO plantVo = mapper.getOneData(userid, plantid); //현재온습도
		GuideVO guideVo = mapper.getOneGuide(userid, plantid); //적정온습도
		
		Map<String, Object> response = new HashMap<>();
		response.put("plantVo", plantVo); 
		response.put("guideVo", guideVo);  
		
		return new Gson().toJson(response);
	}
	
	
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

	//작물정보조회 guide 테이블 조회
	@ResponseBody
	@RequestMapping("/plant_info")
	public String plant(String userid, String plantid, Date plant_date) {
		Map<String, Object> response = new HashMap<>();
		
		GuideVO guideVo = mapper.getPlantStandardInfo(userid, plantid, plant_date); //작물 이름
		response.put("plantName", guideVo); //GuideVO 데이터
		MemberVO memberVo = Membermapper.getOneMember(userid);
		response.put("memberName", memberVo); //GuideVO 데이터
		return new Gson().toJson(response);
		
//		MemberVO member = new Gson().fromJson(vo, MemberVO.class);
//		member = Membermapper.getOneMember(member.getUserid());
//		return new Gson().toJson(member);
	}
	
	
}
