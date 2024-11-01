package kr.co.farm.diary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.farm.common.PageVO;

@Mapper
public interface DiaryMapper {
	
	 // CRUD
    int registerDiary(DiaryVO vo);             // 신규 관찰일지 등록
    List<DiaryVO> getListOfDiary();            // 관찰일지 목록 조회
    List<DiaryVO> getListOfDiaryByWriter(String writer); // 특정 writer에 대한 관찰일지 목록 조회
    DiaryVO getOneDiary(int diary_id);         // 관찰일지 정보 조회
    int updateDiary(DiaryVO vo);               // 관찰일지 정보 변경 저장
    int deleteDiary(int diary_id);             // 관찰일지 정보 삭제
    
    List<Object> getListOfDiaryByWriter(String writer,PageVO page, int plant_id); 	//관찰일지 목록조회
    int countOfDiary(String writer, PageVO page, int plant_id);             //관찰일지 페이지 총건수 조회

}
