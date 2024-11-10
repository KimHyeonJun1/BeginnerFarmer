package kr.co.farm.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.farm.common.PageVO;

@Mapper
public interface NoticeMapper {
	int registerNotice(NoticeVO vo);			// 신규 공지글 등록
	List<Object> getListOfNotice(PageVO page);	// 공지글 목록조회
	NoticeVO getOneNotice(int id);				// 공지글 정보조회
	int updateNotice(NoticeVO vo);				// 공지글 정보변경저장
	int deleteNotice(int id);					// 공지글 정보삭제
	int updateReadCount(int id);				// 조회수 변경
	int countOfNotice(PageVO page);				// 공지글 총건수조회
}

