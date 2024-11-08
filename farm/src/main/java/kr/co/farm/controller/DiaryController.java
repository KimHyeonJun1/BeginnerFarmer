package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import kr.co.farm.auth.LoginUser;
import kr.co.farm.common.CommonUtility;
import kr.co.farm.common.PageVO;
import kr.co.farm.diary.DiaryMapper;
import kr.co.farm.diary.DiaryVO;
import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller @RequestMapping("/diary")
public class DiaryController {
    private final DiaryMapper diaryMapper;
    private final CommonUtility common;
    private final ManageMapper manageMapper;
    
    
    
    
    //관찰일지 수정저장처리 요청
    @ResponseBody @PutMapping("/modify")
    public boolean modify(@RequestBody DiaryVO vo){
    	//원래 관찰일지 조회해오기
//    	 DiaryVO diary = diaryMapper.getOneDiary(vo.getDiary_id() );
    	//화면에서 입력한 정보 DB 변경 저장
    	 
    	return diaryMapper.updateDiary(vo)==1 ? true:false ;
    	//응답화면연결 : 정보화면
    	 
    }
    
    //관찰일지 수정화면 요청
    @GetMapping("/modify")
    public String modify(Authentication user, int id, Model model, HttpSession session) {
    	//해당 관찰일지  DB 조회해 수정화면에 출력할 수 있도록 Model객체에 담기
    	model.addAttribute("diary", diaryMapper.getOneDiary(id));    	
    	session.setAttribute("category", "di");
    	
    	//등록된 작물들 조회
    	String userid_log = user.getName();
    	List<ManageVO> plant = manageMapper.getUserPlants(userid_log);
    	model.addAttribute("plant",plant);
    	
    	//선택한 관찰일지 DB 정보 조회해오기
    	 DiaryVO vo = diaryMapper.getOneDiary(id);
    	 model.addAttribute("vo", vo);
    	
    	return "diary/modify";
    	
    }

    
    //관찰일지 삭제처리 요청
    @DeleteMapping("/delete")
    public String delete(int id) {
    	//해당 관찰일지 DB 삭제 -> 응답화면: 목록
    	if(diaryMapper.deleteDiary(id)==1) {
    	}
    	StringBuffer redirect = new StringBuffer("redirect:list");
    	return redirect.toString();
    }
    
    
    //관찰일지 정보화면 요청
    @RequestMapping("/info")
    public String info(int id ,Model model, HttpSession session) {
    	//선택한 글 DB 조회해 정보화면에 출력하기 위해 Model객체 담기
    	model.addAttribute("diary", diaryMapper.getOneDiary(id));    	
    	session.setAttribute("category", "di");
    	return "diary/info";
    }
    
    
    @ResponseBody @PostMapping("/summernote_image/upload")
    public Object summernoteImageUpload(MultipartFile file, HttpServletRequest request) {
    	String url = common.fileUpload("diary/summernote", file, request);
    	return url;
    }
    
    //관찰일지 글쓰기 저장처리 요청
  	@ResponseBody @PostMapping("/register")
  	public Object register(@RequestBody DiaryVO vo
  							, HttpSession session
  							, HttpServletRequest request
  							, @AuthenticationPrincipal LoginUser user) {
  		//화면에서 입력한 정보로 DB에 신규저장 후 목록화면으로 연결
  		vo.setWriter(user.getUsername() );
  		
  		session.setAttribute("category", "di");
  		return diaryMapper.registerDiary(vo) == 1? true : false;
  	}
  	
  	//관찰일지 글쓰기 화면 요청
    @GetMapping("/register")
    public String register(Authentication user, Model model) {
    	//등록된 작물들 조회
    	String userid_log = user.getName();
    	List<ManageVO> plant = manageMapper.getUserPlants(userid_log);
    	model.addAttribute("plant",plant);
    	
    	return "diary/register";
    }

    //관찰일지 목록 화면 
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "-1") int plant_id, PageVO page, Authentication user, Model model, HttpSession session) {
    	session.setAttribute("category", "di");
        if (user == null) {
            return "diary/diary"; // 로그인하지 않은 경우
        } else {
            session.setAttribute("category", "di");
            // 로그인한 사용자의 writer와 일치하는 관찰일지 목록 조회
            String writer = user.getName();

            // 총 목록 수를 카운트하여 PageVO에 설정
            int totalDiaryCount = diaryMapper.countOfDiary(writer ,page, plant_id);
            page.setTotalList(totalDiaryCount);

            // writer에 대한 관찰일지 목록 조회
            List<Object> diaryList = diaryMapper.getListOfDiaryByWriter(writer, page, plant_id);
            page.setList(diaryList); // 목록 설정

            // 모델에 데이터 추가
            model.addAttribute("diaryList", diaryList); // 실제 목록을 추가
            model.addAttribute("page", page); // 페이지 정보를 추가
            
            //등록된 작물들 조회
        	String userid_log = user.getName();
        	List<ManageVO> plant = manageMapper.getUserPlants(userid_log);
        	model.addAttribute("plant",plant);
        	model.addAttribute("plant_id",plant_id);
        	
        	

            return "diary/list"; // 반환할 뷰 이름
        }
    }

	
	
	
}
