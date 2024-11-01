package kr.co.farm.log;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.farm.common.PageVO;
import kr.co.farm.guide.GuideVO;

@Mapper
public interface LogMapper   {
	 //CRUD
	   
	
	List<HashMap<String, Object>> getCountByTemperature();	//온도 
	List<HashMap<String, Object>> getCountByHumid();	//습도
	List<HashMap<String, Object>> getCountByMoisture();	//토양습도
	List<HashMap<String, Object>> getCountByBright();	//조도
		
	int countOfWater(PageVO page);             //급수관리 작물별 건수 조회
	
	
	//	급수관리화면 목록조회
	WaterVO getOneWaterInfo(int plantid_log);
	
	
	List<Object> getListOfWater(PageVO page); 	//급수관리목록조회
	//급수관리화면 목록조회
	List<WaterVO> getListOfWater(String userid, int plantid_log);
	
	//플랜테이블 목록 조회
	GuideVO getPlantStandardInfo(int plantid_log);
	
//		사용자가 선택한 작물정보조회
	  LogVO getOneLog (int plnatid_log);
	   

	   
}
