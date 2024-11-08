package kr.co.farm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.guide.GuideVO;
import kr.co.farm.manage.ManageMapper;
import kr.co.farm.manage.ManageVO;
import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller	@RequestMapping("/manage") @RequiredArgsConstructor
public class ManageController {

	private final ManageMapper mapper;
	private final MemberMapper memberMapper;	
	
	@ResponseBody
	@RequestMapping("/device-plant")
	public Object device_plant(Authentication user, ManageVO vo) {
		vo.setUserid_log(user.getName());
		List<GuideVO> plantList = mapper.getListOfPlantByDevice(vo); //작물목록 조회-사용자
		return plantList;
	}
	@ResponseBody
	@RequestMapping("/device-user")
	public Object device_user(Authentication user, ManageVO vo) {
		return 	mapper.countOfUserDevice(vo.getUserid(), vo.getMac_address()) == 0 ? false : true; //작물목록 조회-사용자
	}
	
	//작물 정보 화면 요청
	@RequestMapping("/info")
	public String info(Authentication user, Model model, HttpSession session) {
		String userid_log = user.getName();
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");
		List<ManageVO> plant = mapper.getUserPlants(userid_log); 
		model.addAttribute("plant", plant); 
		return "manage/info";
	}
	
	//관리자 디바이스 제품 등록 처리 요청
	@PostMapping("/device_admin_conn")
	public String adminSign(Authentication user, ManageVO vo, HttpSession session) {
		
		mapper.registerDevice(vo);
		return "redirect:device_admin";
	}
	//관리자 디바이스 제품 등록 처리 해제 요청
	@PostMapping("/device-disconn")
	public String device_disconn(Authentication user, ManageVO vo, HttpSession session) {
		mapper.updateDeviceDisconn(vo);
		return "redirect:device_admin";
	}
	//사용자 디바이스 제품 등록 처리 요청
	@PostMapping("/device_user_conn")
	public String userSign(Authentication user, ManageVO vo, HttpSession session) {
		
		mapper.registerUserPlant(vo);
		return "redirect:info";
	}
//	//사용자 디바이스 제품 등록 처리 해제 요청
	@PostMapping("/device-disconn_user")
	public String device_disconn_user(Authentication user, ManageVO vo, HttpSession session) {
		mapper.updateUserDeviceDisconn(vo);
		return "redirect:info";
	}
	//디바이스 등록 화면 요청 관리자
	@GetMapping("/device_admin")
	public String device(Authentication user, ManageVO vo, Model model, HttpSession session) {
		String userid_log = user.getName();
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");

		List<ManageVO> deviceList = mapper.getListOfDevice();
		model.addAttribute("deviceList", deviceList);
		
	    List<MemberVO> memberList = mapper.getListOfMember(); //회원목록 조회-관리자
		model.addAttribute("memberList", memberList);
		
		return "manage/device_admin";
	}
	//디바이스 등록 화면 요청 사용자
	@GetMapping("/device_user")
	public String device_user(Authentication user, ManageVO vo, Model model, HttpSession session) {
		String userid_log = user.getName();
		Integer plantid_log = (Integer) session.getAttribute("plantid_log");
		
		List<ManageVO> deviceList = mapper.getListOfDevice(userid_log);
		model.addAttribute("deviceList", deviceList);
		
		return "manage/device_user";
		
	}
	
	

	   
//	   		//작물목록 화면 요청, 정보가 등록되어 있으면 info 없으면 list화면
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
	
	

