package kr.co.farm.relay;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelayMapper {
	 
	 
//	 급수관리정보 추가
	 int registerWaterManage(String userid_log, int plantid_log);
	 
	 // 릴레이1 값 변경하기
	 int updateRelay1Status(String mac_address, String relay1 );
	 
	 // 릴레이2 값 변경하기
	 int updateRelay2Status(String mac_address, String relay2 );
	 
	 // mac_address 조회하기
	 String getMacAddress(String userid_log, int plantid_log);
	 
	 // light 값 변경하기
	 int updateLightStatus(String mac_address, String light);
	 
}
