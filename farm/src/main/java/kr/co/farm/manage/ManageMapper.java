package kr.co.farm.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageMapper {
//작물관리
	
	int registerManage(ManageVO vo); //신규작물등록
	List<ManageVO> getListOfManage(); //작물목록조회
	ManageVO getOneManage(int plant_id);	//작물정보조회
	int deleteManage(int plant_id);		//작물정보삭제
	
	void registerUserPlant(@Param("userid_log") String userid, @Param("plantid_log") int plant_id);
	void deleteUserPlant(@Param("userid_log") String userid, @Param("plantid_log") int plantid_log);
	List<ManageVO> getUserPlants(@Param("userid_log") String userid_log);
	
	
	
}
