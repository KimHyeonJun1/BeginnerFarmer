package kr.co.farm.arduino;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArduinoMapper {
	
	int arduinoWater(PlantLogVO vo);
	
	int arduinoSave(PlantLogVO vo);
	
	int arduinoUpdate(PlantLogVO vo);
	
	PlantLogVO getOneRelay(String mac_address);
	
}
