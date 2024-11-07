package kr.co.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.arduino.ArduinoMapper;
import kr.co.farm.arduino.PlantLogVO;
import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor	
public class ArduinoController {
	
	private final ArduinoMapper mapper;
	
	
	@ResponseBody @RequestMapping("/relay")
	public String ligntOnOff(String f01) {
		PlantLogVO vo = mapper.getOneRelay(f01);
		
		System.out.println(f01 );
		return vo.getRelay1()+vo.getRelay2()+vo.getRelay3()+vo.getRelay4();
		
	}
	@ResponseBody @RequestMapping("/arduino")
	public void plantSignal(String f01, String f03, String f04, String f05) {
		System.out.println(f01 + " 온도 " + f03 +" 습도 " + f04 +" 조도 " + f05);
		if(f01 == null) {
			return ;
		}
		PlantLogVO vo = new PlantLogVO();
		vo.setMac_address(f01);
		vo.setTemperature(Double.parseDouble(f03));
		vo.setHumid(Double.parseDouble(f04));
		vo.setBright(Integer.parseInt(f05));
		
		mapper.arduinoSave(vo);
		mapper.arduinoUpdate(vo);
	}
}
