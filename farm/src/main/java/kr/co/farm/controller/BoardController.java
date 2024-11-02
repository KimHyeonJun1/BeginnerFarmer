package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	private final CommonUtility common;
	
	// 게시판 정보화면 요청
	@RequestMapping("/info")
	public String info(int id, Model model) {
		
		// 선택한 방명록 글을 DB에서 조회해와서 정보화면에 출력할 수 있게 Model 객체에 담기
		BoardVO vo = mapper.getOneBoard(id);
		model.addAttribute("vo", vo);
		
		return "board/info";
		
	}
	
	// 게시판 등록화면 저장요청
	@PostMapping("/register")
	public String register(@RequestBody BoardVO vo, HttpServletRequest request, Authentication auth) {
		vo.setBoard_writer(auth.getName()); // 현재 로그인한 사용자 ID를 작성자로 설정
		mapper.registerBoard(vo);
		
		return "redirect:list";
	}
	
	// 게시판 등록화면 요청
	@GetMapping("/register")
	public String register(Model model) {
		
		List<BoardTypeVO> boardTypes = mapper.getBoardTypes(); // mapper를 통해 DB에서 게시판 종류 목록을 가져옴
		model.addAttribute("boardTypes", boardTypes); // 조회한 게시판 종류 목록을 모델에 추가
		return "board/register";
	}	
	
	// 특정 유형의 게시판 목록 화면
	@RequestMapping("/listType")
	public String listType(Model model, PageVO page, @RequestParam int board_type_id) {
		
		// 선택된 board_type_id에 해당하는 게시글만 불러오기
	    page.setList(mapper.getListOfBoardType(page, board_type_id));
		
		List<BoardTypeVO> boardTypes = mapper.getBoardTypes();
		model.addAttribute("boardTypes", boardTypes);
		model.addAttribute("page", page);
		
		return "board/list";
	}
	
	// 전체 게시판 목록 화면
	@RequestMapping("/list")
	public String list(HttpSession session, Model model, PageVO page) {
		session.setAttribute("category", "bo");
		
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
