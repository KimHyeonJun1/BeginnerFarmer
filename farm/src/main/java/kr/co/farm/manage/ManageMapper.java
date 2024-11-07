package kr.co.farm.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.farm.member.MemberVO;

@Mapper
public interface ManageMapper {
//작물관리
	
	 // 현재 사용자가 소유한 모든 작물 리스트 조회
    List<ManageVO> getUserPlant(String userid_log);

    // 특정 plantid_log에 대한 작물 정보 조회
    ManageVO getPlantInfo(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
	
	
	ManageVO getOneManage(int plantid_log);
	
	
	
	
	//userplant의 userid_log 조회
	int countOfUserPlant(String userid_log); 
	
	//자신이 등록한 작물 빼고 작물 목록 조회
    List<ManageVO> getListOfManage(String userid_log);
    
    //전체 작물 조회
    List<ManageVO> getListOfManage(int plant_id);
    
    
    
    // 사용자별 작물 조회
    List<ManageVO> getUserPlants(@Param("userid_log") String userid_log);
    
    // 작물 등록
    void registerUserPlant(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
    
    // 작물 삭제
    void deleteUserPlant(@Param("userid_log") String userid_log, @Param("plantid_log") int plantid_log);
	
//	// 선택한 유저의 정보 조회
//    ManageVO getOneManage(String userid_log, int plantid_log);
	
//    디바이스 전체조회
    List<ManageVO> getListOfDevice();
    //자신의 디바이스 목록조회
    List<ManageVO> getListOfDevice(String userid);
    
    
    List<ManageVO> getListOfDeviceNotMatch();
    List<MemberVO> getListOfMember();        //회원목록 조회-관리자
    
    
    
    int updateDeviceDisconn(ManageVO vo);
    
    //제품 등록
    int registerDevice(ManageVO vo);
    
}
