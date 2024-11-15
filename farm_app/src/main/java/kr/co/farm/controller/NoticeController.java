package kr.co.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.notice.NoticeMapper;
import kr.co.farm.notice.NoticeVO;
import lombok.RequiredArgsConstructor;

@RestController	@RequestMapping("/notice") @RequiredArgsConstructor
public class NoticeController {
	private final NoticeMapper mapper;
	
	// 앱 공지사항 정보
	@RequestMapping("/info")
	public String info(int id) {
		NoticeVO notice = mapper.getOneNotice(id);	// 공지글 정보
		
		Map<String, Object> response = new HashMap<>();
		response.put("notice", notice); 			// 공지글 정보 데이터 포함
		
		return new Gson().toJson(response);
	}
	
	
	// 앱 공지사항 목록
	@RequestMapping("/list")
	public String list() {
		List<NoticeVO> noticeList = mapper.getListOfNotice();

		Map<String, Object> response = new HashMap<>();
		response.put("noticeList", noticeList); // 공지사항 목록 데이터를 포함
		
		return new Gson().toJson(response); // JSON 형식으로 반환
	}
}
