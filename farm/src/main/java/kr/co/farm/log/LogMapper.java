package kr.co.farm.log;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.farm.guide.GuideVO;
import kr.co.farm.manage.ManageVO;

@Mapper
public interface LogMapper   {
	 //CRUD
	   
	//temperature
	
	
//	급수관리화면 목록조회
	WaterVO getOneWaterInfo(int plantid_log);
	
	//모니터화면 목록조회
	List<TemperatureVO> getListOfTemperature();
	
	GuideVO getPlantStandardInfo(int plantid_log);
	
//		사용자가 선택한 작물정보조회
	  LogVO getOneLog (int plnatid_log);
	   

	   
}
