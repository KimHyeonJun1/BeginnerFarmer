package kr.co.farm.guide;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuideMapper {
	
	List<GuideVO>getListOfGuide(); // 작물가이드 목록화면
	GuideVO getOneGuide(int plant_id); // 작물가이드 정보화면
	// 작물가이드 추가화면
	// 작물가이드 삭제화면

}
