package kr.co.farm.plant;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlantMapper {
	
	//작물선택
	List<ManageVO> getListOfUserPlant(String userid);
	
	
	//급수관리
	List<WaterVO> getListOfWaterByUser(String userid, String plant_id);	 //급수관리화면 목록조회
	PlantVO getOnePlant(String userid, String plant_id);    //회원의 작물 정보 조회

	
	//온습도
	PlantVO getOneData(String userid, String plant_id);   
	GuideVO getOneGuide(String userid, String plant_id);    
	
	GuideVO getPlantStandardInfo(String userid, String plant_id, Date plant_date);
}
