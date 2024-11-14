package kr.co.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.farm.notice.NoticeMapper;
import kr.co.farm.notice.NoticeVO;
import lombok.RequiredArgsConstructor;

@RestController	@RequestMapping("/notice") @RequiredArgsConstructor
public class NoticeController {
	private final NoticeMapper mapper;
	
	@RequestMapping("/list")
	public String list() {
		List<NoticeVO> noticeList = mapper.getListOfNotice();

		Map<String, Object> response = new HashMap<>();
		response.put("noticeList", noticeList); // 공지사항 목록 데이터를 포함
		
		return new Gson().toJson(response); // JSON 형식으로 반환
	}
}
