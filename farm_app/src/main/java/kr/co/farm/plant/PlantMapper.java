package kr.co.farm.plant;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlantMapper {
	


	
	PlantVO getOneData(String userid, String plant_id);    //회원의 작물 데이터 조회
	
	PlantVO getOnePlant(String userid, String plant_id);    //회원의 작물 정보 조회
	
}
