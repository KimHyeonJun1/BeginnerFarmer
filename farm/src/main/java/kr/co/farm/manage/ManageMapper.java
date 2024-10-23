package kr.co.farm.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {
//작물관리
	
	int registerManage(ManageVO vo); //신규작물등록
	List<ManageVO> getListOfManage(); //작물목록조회
	ManageVO getOneManage(int plant_id);	//작물정보조회
	int deleteCustomer(int plant_id);		//작물정보삭제
	
}
