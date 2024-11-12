package kr.co.farm.relay;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelayMapper {
	 
	 
//	 급수관리정보 추가
	 int registerWaterManage(String userid_log, int plantid_log);
	 
	 int updateRelay1Status(String mac_address, String relay1 );
	 
	 int updateRelay2Status(String mac_address, String relay2 );
	 
	 String getMacAddress(String userid_log, int plantid_log); // 이 메서드로 mac_address 조회
	 
	 
	 int updateLightStatus(String mac_address, String light);
	 
}
