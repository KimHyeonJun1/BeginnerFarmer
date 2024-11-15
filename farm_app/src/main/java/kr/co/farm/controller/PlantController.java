package kr.co.farm.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import kr.co.farm.plant.GuideVO;
import kr.co.farm.plant.ManageVO;
import kr.co.farm.plant.PlantMapper;
import kr.co.farm.plant.PlantVO;
import kr.co.farm.plant.RelayVO;
import kr.co.farm.plant.WaterVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/plant")
@RestController
public class PlantController {
	private final PlantMapper mapper;
	private final MemberMapper Membermapper;
	


	@RequestMapping("/lightStatus")
	@ResponseBody
	public Object lightStatus(String userid_log, String plantid_log) {
	    
	 // mac_address를 데이터베이스에서 가져옴
	    PlantVO  plant = mapper.getOneData(userid_log, plantid_log);
	    
	    return  new Gson().toJson(plant);
	    
	}
	@RequestMapping("/lightStatusUpdate")
	@ResponseBody
	public Object lightStatusUpdate(String userid_log, int plantid_log, String signal) {
		
		// mac_address를 데이터베이스에서 가져옴
		String mac_address = mapper.getMacAddress(userid_log, plantid_log);
		
		RelayVO vo = new RelayVO();
		vo.getMac_address();
		
		int on = mapper.updateLightStatus(mac_address,signal.equals("ON")? "LOW02": "OFFOO");
//	    int off = mapper.updateLightStatus(mac_address, "OFFOO");
		HashMap<String, Object> map = new HashMap<>();
		map.put("signal", signal);
		map.put("status", on==1 ? "성공"	:"실패");
		return  new Gson().toJson(map);
		
	}

	
	@RequestMapping("/waterAndRelay1Update")
	@ResponseBody
	public Object waterAndRelayUpdate(String userid_log, int plantid_log) {

	    // 물주기 기록을 저장
	    mapper.registerWaterManage(userid_log, plantid_log);
	    
	    // mac_address를 데이터베이스에서 가져옴
	    String mac_address = mapper.getMacAddress(userid_log, plantid_log);
	    
	    // relay1을 "ONOOO"로 설정
	    RelayVO vo = new RelayVO();
	    vo.getMac_address();
	    int update = mapper.updateRelay1Status(mac_address, "ONOOO");

	    // 5초 후 relay1을 "OFFOO"로 설정
	    new Timer().schedule(new TimerTask() {
	        @Override
	        public void run() {
	            mapper.updateRelay1Status(mac_address, "OFFOO");
	        }
	    }, 5000);
	    
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("waterList", mapper.getListOfWaterByUser(userid_log, String.valueOf(plantid_log))); // 급수 관리 데이터 리스트
	    response.put("process", update == 1 ? true:false );  
	    return new Gson().toJson(response);
	}
	
	
	//작물배열 가져오기
	@RequestMapping("/userplant")
	public String userplant (String userid){
		List<ManageVO> plantList = mapper.getListOfUserPlant(userid);
		return new Gson().toJson(plantList);
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
	
	
	//급수관리 , 물주기 버튼
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
