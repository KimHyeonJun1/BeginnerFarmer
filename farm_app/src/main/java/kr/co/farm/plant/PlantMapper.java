package kr.co.farm.plant;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlantMapper {
	


	List<WaterVO> getListOfWaterByUser(String userid, String plant_id);	 //급수관리화면 목록조회
	PlantVO getOnePlant(String userid, String plant_id);    //회원의 작물 정보 조회

	
	PlantVO getOneData(String userid, String plant_id);   
	GuideVO getOneGuide(String userid, String plant_id);    
	
	
}
