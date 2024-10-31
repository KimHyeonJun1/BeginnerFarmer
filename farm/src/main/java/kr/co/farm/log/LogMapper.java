package kr.co.farm.log;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.farm.manage.ManageVO;

@Mapper
public interface LogMapper   {
	 //CRUD
	   
	//temperature
	//모니터화면 목록조회
	List<TemperatureVO> getListOfTemperature();
	
	
	
	
//		사용자가 선택한 작물정보조회
	  LogVO getOneLog (int plnatid_log);
	   
//	    유저의 작물이 등록된지 며칠됐는지 알려주는 쿼리 
	   List<LogVO> getCountDate(ManageVO vo);
	   
}
