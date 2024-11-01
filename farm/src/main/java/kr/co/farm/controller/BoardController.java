package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.farm.board.BoardMapper;
import kr.co.farm.board.BoardTypeVO;
import kr.co.farm.board.BoardVO;
import kr.co.farm.common.CommonUtility;
import kr.co.farm.common.PageVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/board")
public class BoardController {
	
	private final BoardMapper mapper;
//	private final CommonUtility common;
	
	// 게시판 등록화면 요청
	@GetMapping("/register")
	public String register() {
		
		return "board/register";
	}
	
	// 게시판 정보화면 요청
	@RequestMapping("/info")
	public String info(int id, Model model) {
		
		// 선택한 방명록 글을 DB에서 조회해와서 정보화면에 출력할 수 있게 Model 객체에 담기
		BoardVO vo = mapper.getOneBoard(id);
		model.addAttribute("vo", vo);
		
		return "board/info";
		
	}
	
	// 게시판 목록화면 요청
	@RequestMapping("/list")
	public String list(HttpSession session, Model model, PageVO page, @RequestParam(defaultValue = "-1") int board_type_id) {
		session.setAttribute("category", "bo");
		
		model.addAttribute("board_type_id", board_type_id);
		
		// 게시판 종류를 DB에서 조회해오기
		List<BoardTypeVO> boardTypes = mapper.getBoardTypes();
		model.addAttribute("boardTypes", boardTypes);
		
		// DB에서 게시판 목록을 조회해와서 화면에 출력할 수 있도록 Model 객체에 담기
		page.setTotalList( mapper.getCountOfBoard(page) );
		page.setList( mapper.getListOfBoard(page) );
		model.addAttribute("page", page);
		
		return "board/list";
	}
}
