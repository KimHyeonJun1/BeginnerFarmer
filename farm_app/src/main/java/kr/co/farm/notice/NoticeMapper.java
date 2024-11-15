package kr.co.farm.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	List<NoticeVO> getListOfNotice();		// 공지글 목록조회
	NoticeVO getOneNotice(int id);		// 공지글 정보조회
}

