package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	// 게시판 삭제처리
	@DeleteMapping("/delete")
	public String delete(int board_id) {
		mapper.deleteBoard(board_id);
		StringBuffer redirect = new StringBuffer("redirect:list");
		
		return redirect.toString();
	}
	
	// 게시판 수정 저장처리 요청
	@ResponseBody @PutMapping("/modify")
    public boolean modify(@RequestBody BoardVO vo){
    	 
    	return mapper.updateBoard(vo)==1 ? true:false;
    	//응답화면연결 : 정보화면
    }
	
	// 게시판 수정화면 요청
	@RequestMapping("/modify")
	public String modify(int board_id, Model model) {
		// 해당 게시판 정보를 DB에서 조회하고 수정화면 요청을 위해 Model 객체에 담기
		model.addAttribute("vo", mapper.getOneBoard(board_id));
		// 게시판 종류 정보를 DB에서 조회하여 Model 객체에 담기 
		model.addAttribute("boardTypes", mapper.getBoardTypes());
		return "board/modify";
	}
	
	// 게시판 정보화면 요청
	@RequestMapping("/info")
	public String info(int board_id, Model model, int board_type_id) {
		// 조회수 증가
		mapper.updateReadCount(board_id);
		model.addAttribute("board_type_id", board_type_id);
		// 선택한 게시판 글을 DB에서 조회해와서 정보화면에 출력할 수 있게 Model 객체에 담기
		BoardVO vo = mapper.getOneBoard(board_id);
		model.addAttribute("vo", vo);
		
		return "board/info";
	}
	
	// 서머노트 파일 업로드
	@ResponseBody @PostMapping("/summernote_image/upload")
    public Object summernoteImageUpload(MultipartFile file, HttpServletRequest request) {
    	String url = common.fileUpload("board/summernote", file, request);
    	return url;
    }
	
	// 게시판 등록화면 저장처리
	@ResponseBody @PostMapping("/register")
	public boolean register(@RequestBody BoardVO vo, Authentication auth) {
		vo.setBoard_writer(auth.getName()); // 현재 로그인한 사용자 ID를 작성자로 설정
		
		return mapper.registerBoard(vo) == 1? true : false;
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
		
		List<BoardTypeVO> boardTypes = mapper.getBoardTypes();
		model.addAttribute("boardTypes", boardTypes);
		model.addAttribute("page", page);
		
		return "board/list";
	}
	
	// 전체 게시판 목록 화면
	@RequestMapping("/list")
	public String list(HttpSession session, Model model, PageVO page, @RequestParam (defaultValue = "0") int board_type_id) {
		session.setAttribute("category", "bo");
		
		model.addAttribute("board_type_id", board_type_id);
		// 선택한 게시판 종류 총 목록수 조회
		page.setTotalList( mapper.getCountOfBoardType(page, board_type_id) );
		// 선택된 board_type_id에 해당하는 게시글만 불러오기
		page.setList(mapper.getListOfBoardType(page, board_type_id));
		
		// 게시판 종류를 DB에서 조회해오기
		List<BoardTypeVO> boardTypes = mapper.getBoardTypes();
		model.addAttribute("boardTypes", boardTypes);
		
		// DB에서 게시판 목록을 조회해와서 화면에 출력할 수 있도록 Model 객체에 담기
//		page.setTotalList( mapper.getCountOfBoard(page) );
//		page.setList( mapper.getListOfBoard(page) );
		model.addAttribute("page", page);
		
		return "board/list";
	}
}
