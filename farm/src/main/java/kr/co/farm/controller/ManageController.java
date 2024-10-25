package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.AllArgsConstructor;

@Controller	@RequestMapping("/manage") @AllArgsConstructor
public class ManageController {
	private ManageMapper mapper;
	
	
	@RequestMapping("/delete")
	public String delete(Authentication user,@RequestParam("userid_log") String userid_log, ManageVO vo, HttpSession session) {
        String userid = user.getName();
        int plantid_log = vo.getPlant_id();
        mapper.deleteUserPlant( userid_log, plantid_log);
        return "redirect:/manage/info";
    }
	
	
	
//	@RequestMapping("/delete")
//	public String delete(int plantid_log, String userid_log) {
//		mapper.deleteManage(userid_log, plantid_log);
//		return "redirect:info";
//	}
	
	
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
//	        if(userid_log == null) {
//	            return "redirect:/login";
//	        }
	        int plantid_log = vo.getPlant_id();
	        mapper.registerUserPlant(userid_log, plantid_log);
	        return "redirect:info";
	   }
	
	   //신규작물등록 화면 요청
	   @GetMapping("/register")
	   public String register(Model model, HttpSession session) {
	        String userid_log = (String) session.getAttribute("userid_log");
//	        if(userid_log == null) {
//	            return "redirect:/login";
//	        }
//	        List<ManageVO> plant = mapper.getListOfUserPlant(); //방금 수정한거
	        List<ManageVO> plant = mapper.getListOfManage(); //기존꺼
	        model.addAttribute("plant", plant);
	        return "manage/register";
	    }
//	   public String register(Model model,  HttpSession session) {
//		 List<ManageVO> plant = mapper.getListOfManage();
//		 model.addAttribute("plant", plant);
//		 return "manage/register";
	   
	
	   		@RequestMapping("/list")
	   		public String list(Authentication user, HttpSession session, Model model) {
	   			List<ManageVO> list = mapper.getListOfManage();
	   			model.addAttribute("list", list);
	   			session.setAttribute("category", "ma");
	        
	   			if(user == null ) {
	   				
	   				return "manage/list";
	   			}else {
	   				int count = mapper.countOfUserPlant(user.getName());
	   				if(count == 0) {
	   					
	   					return "manage/list";
	   				}else {
	   					
	   					return "redirect:info";
	   				}
	   			}
	   			

	   }
		   
		
		
	}
	
	

