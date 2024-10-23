package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import lombok.AllArgsConstructor;

@Controller	@RequestMapping("/manage") @AllArgsConstructor
public class ManageController {
	private ManageMapper mapper;
	
	@RequestMapping("/info")
	public String info() {
		return "manage/info";
	}
	
	
	//신규작물 등록 처리 요청
	   @PostMapping("/register")
	   public String register(ManageVO vo, Model model) {
		 mapper.registerManage(vo);
			List<ManageVO> plant = mapper.getListOfManage();
			model.addAttribute("plant", plant);
	      //응답화면
	      return "redirect:list";
	   }
	
	   //신규작물등록 화면 요청
	@GetMapping("/register")
	public String register(Model model) {
		List<ManageVO> plant = mapper.getListOfManage();
	    model.addAttribute("plant", plant);
		return "manage/register";
	}
	
	
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		session.setAttribute("category", "ma");
		
//		if( userid == null ) {
//			return "redirect:login";
//		}
//		
		List<ManageVO> plant = mapper.getListOfManage();
		
		if(plant.isEmpty() ) {
			return "manage/info";
		}else {
			model.addAttribute("plant", plant);
			return "manage/list";
		}
		
		
		
//		return "manage/list";
	}
	
	
}
