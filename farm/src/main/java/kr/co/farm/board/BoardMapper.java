package kr.co.farm.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.farm.common.PageVO;

@Mapper
public interface BoardMapper {
	List<Object> getListOfBoard(PageVO page);						 // 게시판 목록조회 - 페이지처리
	int getCountOfBoard(PageVO page);								 // 게시판 전체목록수 조회
	int registerBoard(BoardVO vo);									 // 게시판 신규등록
	BoardVO getOneBoard(int board_id); 								 // 게시판 정보조회
	int updateBoard(BoardVO vo);									 // 게시판 변경저장
	int updateReadCount(int board_id);								 // 게시판 조회수 변경저장
	int deleteBoard(int board_id);					 				 // 게시판 삭제
	
	List<BoardTypeVO> getBoardTypes();		 		 			 	 // 게시판 종류 목록
	List<Object> getListOfBoardType(PageVO page, int board_type_id); // 특정 게시판에 속한 게시판 목록조회
	int getCountOfBoardType(PageVO page, int board_type_id);		 // 특정 게시판 전체 목록수 조회
}
