package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.diary.DiaryMapper;
import kr.co.farm.diary.DiaryVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/diary")
public class DiaryController {
    private final DiaryMapper diaryMapper;
    
    
    //관찰일지 글쓰기 저장처리 요청
  	@PostMapping("/register")
  	public String register(DiaryVO vo
//  							, MultipartFile[] files
  							, HttpSession session
  							, Authentication user
  							, HttpServletRequest request ) {
  		//화면에서 입력한 정보로 DB에 신규저장 후 목록화면으로 연결
  		vo.setWriter(user.getName() );
//  		vo.setFileList( common.fileUpload("diary", files, request));
  		
  		session.setAttribute("category", "di");
  		diaryMapper.registerDiary(vo);
  		
  		return "redirect:list";
  	}

    //관찰일지 글쓰기 화면 요청
    @GetMapping("/register")
    public String register() {
    	return "diary/register";
    }
    
    //관찰일지 목록 화면 요청
	@GetMapping("/list")
    public String list( Authentication user, Model model, HttpSession session) {
        // 로그인한 사용자의 writer와 일치하는 관찰일지 목록 조회
        String writer = user.getName();
        List<DiaryVO> diaryList = diaryMapper.getListOfDiaryByWriter(writer);
        model.addAttribute("diaryList", diaryList);
		session.setAttribute("category", "di");
        return "diary/list"; 
    }
	
	
	
	
}
