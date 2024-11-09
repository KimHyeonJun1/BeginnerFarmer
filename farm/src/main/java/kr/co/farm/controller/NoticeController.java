package kr.co.farm.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.farm.common.CommonUtility;
import kr.co.farm.common.PageVO;
import kr.co.farm.notice.NoticeMapper;
import kr.co.farm.notice.NoticeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/notice")
public class NoticeController {
	private final NoticeMapper mapper;
	private final CommonUtility common;
	
	// 공지글 삭제처리 요청
	@DeleteMapping("/delete")
	public String delete(int id, HttpServletRequest request, PageVO page) throws Exception {
		String filepath = mapper.getOneNotice(id).getFilepath();
		// 해당 공지글 정보를 DB에서 삭제 -> 응답화면: 목록
		if( mapper.deleteNotice(id)==1) {
//			common.fileDelete(filepath, request);
		}
		
		StringBuffer redirect = new StringBuffer("redirect:list");
		redirect.append("?pageNo=").append( page.getPageNo() )
				.append("&search=").append( page.getSearch() )
				.append("&keyword=").append( URLEncoder.encode(page.getKeyword(), "utf-8") );
		return redirect.toString();
	}
	
	// 공지글 수정저장처리 요청
	@PutMapping("/modify")
	public String modify(NoticeVO vo, PageVO page) throws Exception{
		// 화면에서 입력한 정보로 DB에 변경저장
		mapper.updateNotice(vo);
		// 응답화면 연결: 정보화면
		StringBuffer redirect = new StringBuffer("redirect:info");
		redirect.append("?id=").append( vo.getId() )
			    .append("&pageNo=").append( page.getPageNo() )
			    .append("&search=").append( page.getSearch() )
			    .append("&keyword=").append( URLEncoder.encode(page.getKeyword(), "utf-8") );
		
		return redirect.toString();
	}
	
	// 공지글 수정화면 요청
	@GetMapping("/modify")
	public String modify(int id, PageVO page, Model model, HttpServletRequest request) {
		// 해당 공지글 정보를 DB에서 조회해와서 수정화면에 출력할 수 있도록 Model객체에 담기
		model.addAttribute("page", page);
		NoticeVO vo = mapper.getOneNotice(id);
		model.addAttribute("vo", vo);
//			common.fileExist(vo.getFilepath(), model, request);
		return "notice/modify";
	}
	
	// 공지글 정보화면 요청
	@RequestMapping("/info")
	public String info(int id, Model model, PageVO page) {
		mapper.updateReadCount(id);		// 조회수 증가 처리
		// 해당 공지글 정보를 DB에서 조회해오기 -> 정보화면에 출력할 수 있도록 Model객체에 담기
//			NoticeVO vo = mapper.getOneNotice(id);
		model.addAttribute("vo", mapper.getOneNotice(id));
		model.addAttribute("page", page);
		model.addAttribute("crlf", "\r\n");
		return "notice/info";
	}
	
	// 공지글 저장처리 요청
	@PostMapping("/register")
	public String register(NoticeVO vo, MultipartFile file, HttpServletRequest request) {
		// 첨부파일이 있는 경우
		if( !file.isEmpty() ) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.fileUpload("notice", file, request));
		}
		// 화면에서 입력한 정보로 DB에 신규저장처리 -> 응답화면:목록
		mapper.registerNotice(vo);
		
		return "redirect:list";
	}
	
	// 공지글쓰기 화면 요청
	@GetMapping("/register")
	public String register() {
		
		return "notice/register";
	}
	
	// 공지글 목록 화면 요청
	@RequestMapping("/list")
	public String list(HttpSession session, Model model, PageVO page ) {
		session.setAttribute("category", "no");
		
		// DB에서 공지글목록을 조회해오기 -> 화면에 출력할 수 있도록 Model객체에 담기
 		page.setTotalList(mapper.countOfNotice(page));
		page.setList(mapper.getListOfNotice(page));
		model.addAttribute("page", page);
		
		return "notice/list";
	}
}
