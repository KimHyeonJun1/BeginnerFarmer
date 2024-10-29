package kr.co.farm.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageMapper {
//작물관리
	
	
	ManageVO getOneManage(int plantid_log);
	
	// 특정 작물 정보 조회 (새로운 메서드 추가)
//    ManageVO selectPlantInfo(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
	
	//userplant의 userid_log 조회
	int countOfUserPlant(String userid_log); 
	
	//자신이 등록한 작물 빼고 작물 목록 조회
    List<ManageVO> getListOfManage(String userid_log);
    
    //전체 작물 조회
    List<ManageVO> getListOfManage();
    
    
    // 사용자별 작물 조회
    List<ManageVO> getUserPlants(@Param("userid_log") String userid_log);
    
    // 작물 등록
    void registerUserPlant(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
    
    // 작물 삭제
    void deleteUserPlant(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
	
//	// 선택한 유저의 정보 조회
//    ManageVO getOneManage(String userid_log, int plantid_log);
	
}
