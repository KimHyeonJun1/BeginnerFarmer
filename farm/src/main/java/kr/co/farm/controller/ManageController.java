package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.RequiredArgsConstructor;

@Controller	@RequestMapping("/manage") @RequiredArgsConstructor
public class ManageController {
	private final ManageMapper mapper;
		
	
	
	//작물 정보 삭제 요청
	@DeleteMapping("/delete")
	public String delete(Authentication user, int plantid_log, Model model) {
		String userid_log = user.getName();
        mapper.deleteUserPlant(userid_log, plantid_log);
        return "redirect:info";
    }
	

	
	
	//작물 정보 화면 요청
	@RequestMapping("/info")
	 public String info(Authentication user, Model model, HttpSession session) {
        String userid_log = user.getName();
        List<ManageVO> plant = mapper.getUserPlants(userid_log); 
        model.addAttribute("plant", plant); 
        return "manage/info";
	}
	
	
	//신규작물 등록 처리 요청
	   @PostMapping("/register")
	   public String register(Authentication user, ManageVO vo, HttpSession session) {
	        String userid_log = user.getName();
	        int plantid_log = vo.getPlant_id();
	        mapper.registerUserPlant(userid_log, plantid_log);
	        return "redirect:info";
	   }
	
	   //신규작물등록 화면 요청
	   @GetMapping("/register")
	   public String register(Authentication user, Model model, HttpSession session) {
		   String userid_log = user.getName();

	        List<ManageVO> plant = mapper.getListOfManage(userid_log); //기존꺼
	        model.addAttribute("plant", plant);
	        return "manage/register";
	    }

	   
	   		//작물목록 화면 요청, 정보가 등록되어 있으면 info 없으면 list화면
	   		@RequestMapping("/list")
	   		public String list(Authentication user, HttpSession session, Model model) {
	   			
	   			session.setAttribute("category", "ma");
	        
	   			if(user == null ) {
	   				
	   				return "manage/list";
	   			}else {
	   				List<ManageVO> list = mapper.getListOfManage(user.getName());
	   				model.addAttribute("list", list);
	   				int count = mapper.countOfUserPlant(user.getName());
	   				if(count == 0) {
	   					
	   					return "manage/list";
	   				}else {
	   					
	   					return "redirect:info";
	   				}
	   			}
	   			

	   }
		   
		
		
	}
	
	

