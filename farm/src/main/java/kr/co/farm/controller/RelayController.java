package kr.co.farm.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.relay.RelayMapper;
import kr.co.farm.relay.RelayVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/relay")
@Controller	@RequiredArgsConstructor
public class RelayController {
	private final RelayMapper mapper;
	
	
		
	@PostMapping("/waterAndRelay1Update")
	@ResponseBody
	public void waterAndRelayUpdate(Authentication user, HttpSession session) {
	    String userid_log = user.getName();
	    Integer plantid_log = (Integer) session.getAttribute("plantid_log");

	    // 물주기 기록을 저장
	    mapper.registerWaterManage(userid_log, plantid_log);
	    
	 // mac_address를 데이터베이스에서 가져옴
	    String mac_address = mapper.getMacAddress(userid_log, plantid_log);
	    
	    // relay1을 "ONOOO"로 설정
	    RelayVO vo = new RelayVO();
	    vo.getMac_address();
	    mapper.updateRelay1Status(mac_address, "ONOOO");

	    // 8초 후 relay1을 "OFFOO"로 설정
	    new Timer().schedule(new TimerTask() {
	        @Override
	        public void run() {
	            mapper.updateRelay1Status(mac_address, "OFFOO");
	        }
	    }, 5000);
	}
	
	//
	@PostMapping("/lightStatus")
	@ResponseBody
	public void lightStatus(Authentication user, HttpSession session, String id) {
		String userid_log = user.getName();
	    Integer plantid_log = (Integer) session.getAttribute("plantid_log");
	    
	 // mac_address를 데이터베이스에서 가져옴
	    String mac_address = mapper.getMacAddress(userid_log, plantid_log);
	    
	    RelayVO vo = new RelayVO();
	    vo.getMac_address();
	    
	    id = id.contains("off") ? "OFFOO" : id.replace("-", "").toUpperCase();
	    mapper.updateLightStatus(mac_address, id);
	    
	}
	
	//릴레이2 변경제어하기
	@PostMapping("/motorAndRelay2Update")
	@ResponseBody
	public void motorAndRelay2Update(Authentication user, HttpSession session, String id) {
	    String userid_log = user.getName();
	    Integer plantid_log = (Integer) session.getAttribute("plantid_log");

	    
	 // mac_address를 데이터베이스에서 가져옴
	    String mac_address = mapper.getMacAddress(userid_log, plantid_log);
	    
	    RelayVO vo = new RelayVO();
	    vo.getMac_address();
	    
	    // relay2값 설정
	    id = id.contains("on") ? "ONOOO" : "OFFOO";
	    mapper.updateRelay2Status(mac_address, id);

	  
	}
}
