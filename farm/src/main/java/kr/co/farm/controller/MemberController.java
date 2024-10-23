package kr.co.farm.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farm.common.CommonUtility;
import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberMapper mapper;
	private final CommonUtility common;
	private final PasswordEncoder password;
	
	
	//회원가입 화면 요청
	@RequestMapping("/join")
	public String join(HttpSession session) {
		session.setAttribute("category", "join");
		return "default/member/join";
	}
	
	//현재 입력비번이 정확한지 확인 요청
		@ResponseBody
		@RequestMapping("/user/correctPassword")
		public boolean correctPassword(String userid, String userpw) {
			//입력한 비번이 DB의 비번과 일치하는지
			MemberVO vo = mapper.getOneMember(userid);
			return password.matches(userpw, vo.getUserpw());
		}
		
	// 비밀번호 변경 화면 요청
		@RequestMapping("/user/changePassword")
		public String changePassword(HttpSession session) {
			session.setAttribute("category", "change");
			return "member/change";
		}
	
	// 임시 비밀번호 발급 처리
		@ResponseBody
		@RequestMapping("/tempPassword")
		public String tempPassword(MemberVO vo) {
			// 화면에서 입력한 아이디와 이메일이 일치하는 회원에게
			vo = mapper.getOneMemberByUseridAndEmail(vo);
			StringBuffer msg = new StringBuffer("<script>");
			if (vo == null) {
				msg.append("alert('아이디나 이메일이 맞지 않습니다. \\n확인하세요!'); ");
				msg.append("location='findPassword' ");
			} else {
				// 임시비번을 생성한 후 회원정보에 변경저장, 임시비번을 이메일로 알려주기
				String pw = UUID.randomUUID().toString().substring(0, 6); // afreq-e243a-adf4a-d3dfd
				vo.setUserpw(password.encode(pw)); // 임시비번을 암호화하여 담기
	
				// DB에 변경저장하기
				if (mapper.updatePassword(vo) == 1 && common.emailForPassword(vo, pw)) {
					// 발급한 임시비번을 메일로 보내기 -> 임시비번을 사용해 로그인하도록 로그인화면 연결
					msg.append("alert('임시 비밀번호가 발급되었습니다. \\n이메일을 확인하세요'); ");
					msg.append("location='login' ");
	
				} else {
					msg.append("alert('임시 비밀번호 발급 실패 ㅠㅠ'); ");
					msg.append("location='findPassword'");
				}
	
			}
			msg.append("</script>");
			return msg.toString();
		}
	
	
	// 비밀번호찾기 화면 요청
		@RequestMapping("/findPassword")
		public String findPassword() {
			return "default/member/find";
		}
		
	
	//로그인실패 처리
		@ResponseBody @RequestMapping("/login/fail")
		public String loginFail(HttpServletRequest request) {
			StringBuffer msg = new StringBuffer("<script>");
			msg.append("alert('아이디나 비밀번호가 일치하지 않습니다.'); ");
			msg.append("location='")
						.append(common.appURL(request, "/member/login")).append("'");
			msg.append("</script>");
			return msg.toString();
		}	

	
	//로그인 화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
//		session.setAttribute("", "login");
		MemberVO vo = null;
		if(vo == null) {
		}
		return "default/member/login";
	}
}
