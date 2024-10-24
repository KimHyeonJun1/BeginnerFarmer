package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("plant_id") int plant_id, HttpSession session) {
	    String userid = (String) session.getAttribute("userid");
	    if (userid == null) {
	        return "redirect:/login";
	    }

	    // 유저와 작물 간의 관계 삭제
//	    mapper.deleteUserPlant(userid, plant_id);

	    // 삭제 후 다시 info 화면으로 리다이렉트
	    return "redirect:/manage/info";
	}
	
	
	
		//작물 정보 화면 요청
	@RequestMapping("/info")
	public String info(Model model, HttpSession session) {
		String userid_log = (String) session.getAttribute("userid_log");
		List<ManageVO> plant = mapper.getUserPlants(userid_log);
		model.addAttribute("plant", plant);
		return "manage/info";
	}
	
	
		//신규작물 등록 처리 요청
	   @PostMapping("/register")
	   public String register(ManageVO vo, Model model, HttpSession session) {
		   String userid_log = (String) session.getAttribute("userid_log");
		   int plantid_log = vo.getPlant_id();
		   mapper.registerUserPlant(userid_log, plantid_log);
		   //화면에서 선택한 정보를 db에 신규저장
	      //응답화면
	      return "redirect:list	";
	   }
	
	   //신규작물등록 화면 요청
	   @GetMapping("/register")
	   public String register(Model model,  HttpSession session) {
		   List<ManageVO> plant = mapper.getListOfManage();
		 model.addAttribute("plant", plant);
		 return "manage/register";
	   }
	
	   @RequestMapping("/list")
	   public String list(HttpSession session, String userid, Model model) {
		   List<ManageVO> list = mapper.getListOfManage();
		   model.addAttribute("list", list);
		   model.addAttribute("userid", userid);
		   session.setAttribute("category", "ma");
		   
		   return "manage/list";
	   }
		   
	   
//	    @RequestMapping("/list")
//		public String list(HttpSession session, Model model) {
//		session.setAttribute("category", "ma");
//		String userid_log = (String) session.getAttribute("userid_log");
//		List<ManageVO> plant = mapper.getUserPlants(userid_log); 
//	
//		if(plant.isEmpty() ) {
//			 List<ManageVO> plants = mapper.getListOfManage(); // 전체 작물 조회
//		        model.addAttribute("plant", plants);
//		        return "manage/register";
//		}else {
//			  model.addAttribute("plant", plant);
//		        return "manage/info";
//		}
		
		
	}
	
	

