package kr.co.farm.auth;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import kr.co.farm.board.BoardMapper;
import lombok.RequiredArgsConstructor;

@Component @RequiredArgsConstructor
public class AccessUser {
	private final BoardMapper board;
	
	// 접근이 허용된 사용자인지 확인
	public boolean is(Authentication auth, HttpServletRequest request) {
		boolean access = false;
		
		String url = request.getRequestURI();
		// 요청 파라미터 확인: 요청시 전송된 파라미터에서 글의 id인 파라미터의 값만 뽑기
		int id = 0;
		Set<String> keys = request.getParameterMap().keySet();
		if( keys.contains("id")) id = Integer.parseInt( request.getParameter("id"));
		
		if( url.contains("board"))
			access = auth.getName().equals( board.getOneBoard(id).getBoard_writer());
		else if( url.contains("board/comment"))
			access = auth.getName().equals( board.getOneComment(id).getWriter());
//		else if( url.contains("notice"))
//			access = auth.getName().equals( board.getOneNotice(id).getWriter());
			
		return access;
	}
}
