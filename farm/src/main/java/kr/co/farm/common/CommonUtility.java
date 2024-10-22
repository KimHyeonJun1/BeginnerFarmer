package kr.co.farm.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.farm.member.MemberVO;

@Service

public class CommonUtility {
	
	@Value("${spring.mail.host}") private String emailHost;
	@Value("${spring.mail.username}") private String emailUser;
	@Value("${spring.mail.password}") private String emailPass;
	
	
		private void mailSender(HtmlEmail sender) {
			sender.setDebug(true);
			sender.setCharset("utf-8");
	
			sender.setHostName(emailHost);
			sender.setAuthentication(emailUser, emailPass); // 이메일주소, 비번
			sender.setSSLOnConnect(true); // 로그인버튼 클릭
		}
	
	
	// 임시비밀번호 이메일 보내기
		public boolean emailForPassword(MemberVO vo, String pw) {
			boolean send = true;

			HtmlEmail sender = new HtmlEmail();
			mailSender(sender);
 
			try {
				sender.setFrom(emailUser, "초보농부 관리자"); // 이메일 보내는이 정보
				sender.addTo(vo.getEmail(), vo.getName());

				sender.setSubject("초보농부 임시비밀번호"); // 제목
				// 내용
				StringBuffer content = new StringBuffer();
				content.append("<h3>[").append(vo.getName()).append("]님 비밀번호가 발급되었습니다.</h3>");
				content.append("<div>아이디: ").append(vo.getUserid()).append("</div>");
				content.append("<div>임시 비밀번호: <strong>").append(pw).append("</strong></div>");
				content.append("<div>발급된 임시 비밀번호로 로그인 후 비밀번호를 변경하세요</dic>");
				sender.setHtmlMsg(content.toString());

				sender.send();// 보내기 버튼 클릭

			} catch (EmailException e) {
				send = false;
			}
			return send;
		}

	
	//서버 기본 URL을 생성, 반환
	public String appURL(HttpServletRequest request) {
		//http://localhost:8080/farm
		//http://127.0.0.1:80/farm
		//http://192.168.0.10:8080/farm
		StringBuffer url = new StringBuffer("http://");
		url.append(request.getServerName() ).append(":"); // localhost 또는 127.0.0.1
		url.append(request.getServerPort() ); // 8080
		url.append(request.getContextPath() ); // farm 
		return url.toString(); //최종적으로 만들어진 URL 문자열로 변환하여 반환
	}
	
	// 기본 URL에 추가적인 경로(path)를 더해 전체 URL 반환
	public String appURL(HttpServletRequest request, String path) {
		// http://127.0.0.1/farm + "/member/login"
		return new StringBuffer(appURL(request)).append(path).toString();

	}
	

}
