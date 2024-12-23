package kr.co.farm.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.farm.auth.LoginUser;
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
	
	//내 정보 변경저장 처리 요청
		@PutMapping("/user/myPage/modify")
		public String myPage(MemberVO vo, boolean img, MultipartFile file, @AuthenticationPrincipal LoginUser auth 
							, HttpSession session, HttpServletRequest request) {
			//원래 프로필정보를 조회해오기
			MemberVO user = mapper.getOneMember(vo.getUserid() );
			
//			//프로필을 첨부하지 않은 경우
//			if( file.isEmpty() ) {
//				// 원래X -> 화면imgX -> 처리X
//				// 원래O -> 화면imgO -> DB저장할 vo에 담기
//				// 원래O -> 화면imgX -> 물리적파일 삭제
//				if( img ) {
//					vo.setProfile( user.getProfile() );
//				}
//				
//			}else {
//			//프로필을 첨부하지 않은 경우
//				//원래X -> 새로첨부 -> 새로첨부 -> DB저장할 profile에 담기
//				//원래O -> 바꿔첨부 -> DB저장할 vo의 profile에 담기 + 물리적파일 삭제
//				vo.setProfile(common.fileUpload("profile", file, request) );
//			}
			//화면에서 입력한 정보로 DB에 변경 저장
			//변경된 정보가 화면에 반영되도록 세션정보를 변경하기
			if( mapper.updateMember(vo) == 1 ) {
				auth.setUser(vo);
					//물리적파일삭제
					//원래O -> 화면imgX -> 물리적파일 삭제
					//원래O -> 바꿔첨부  -> DB 저장할 vo 의 profile에 담기 + 물리적파일 삭제
//				if( user.getProfile() != null ) {
//					if( ! img ) {
//						common.fileDelete( user.getProfile(), request );
//					}
//				}
				session.setAttribute("loginInfo", vo);
			}
			return "redirect:/";
		}
	
	//Principal : 접근주체인 UserDetails(LoginUser)
	// MyPage 화면 요청
		@RequestMapping("/user/myPage")
		public String myPage(HttpSession session, Model model
							, @AuthenticationPrincipal LoginUser user) {
			session.setAttribute("category", "my");

			// 로그인한 사용자정보를 조회해오기
			String userid = user.getUsername();
			model.addAttribute("vo", mapper.getOneMember(userid) ) ;
			return "member/myPage";
		}
	
	// 회원가입처리 요청
	@ResponseBody
	@RequestMapping("/register")
	public String join( MemberVO vo, HttpServletRequest request) {

		// 입력비번 암호화하기
		vo.setUserpw(password.encode(vo.getUserpw()));

		// 화면에서 입력한 정보로 DB에 회원정보저장 처리 -> 로그인/회원가입 화면으로 연결
		StringBuffer msg = new StringBuffer("<script>");

		if (mapper.registerMember(vo) == 1) {
			// 회원가입 축하메시지를 이메일로 보내기
			common.emailForJoin(vo);
			
			msg.append("alert('회원가입을 축하합니다'); ");
			msg.append("location='login'; ");
			// return "redirect:login";
		} else {
			msg.append("alert('회원가입실패'); ");
			msg.append("location='join'; ");
			// return "redirect:join";
		}
		msg.append("</script>");
		return msg.toString();

	}
	
	// 아이디중복확인 요청
	@ResponseBody
	@RequestMapping("/idCheck")
	public boolean idCheck(String userid) {
		return mapper.getOneMember(userid) == null ? true : false;
	}
	
	//회원가입 화면 요청
	@RequestMapping("/join")
	public String join(HttpSession session) {
		session.setAttribute("category", "join");
		return "default/member/join";
	}
	
	// 새 비밀번호로 변경저장 처리 요청
		@ResponseBody
		@RequestMapping("/user/resetPassword")
		public boolean resetPassword(MemberVO vo, String userpw
									, @AuthenticationPrincipal LoginUser user) {
			vo.setUserid( user.getUsername() ); //인증된 사용자 아이디를 글쓴이의 아이디로 담기
			// MemberVo의 id: sinwoo, pw: Abcd1
			vo.setUserpw(password.encode(userpw)); // 입력비번을 암호화하기
			return mapper.updatePassword(vo) == 1 ? true : false;
		}
		
	
	//현재 입력비번이 정확한지 확인 요청
		@ResponseBody
		@RequestMapping("/user/correctPassword")
		public boolean correctPassword(String userid, String userpw
									, @AuthenticationPrincipal LoginUser user) {
			//입력한 비번이 DB의 비번과 일치하는지
			MemberVO vo = mapper.getOneMember(user.getUsername());
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
	public String login(HttpSession session, boolean redirect, HttpServletRequest request) {
		session.setAttribute("category", "login");
		
		if( redirect ) {
			//http://localhost:8080/farm/board/info?id=1&pageNo=1...
			StringBuffer url = new StringBuffer( request.getHeader("referer") );
			if( ! request.getHeader("referer").contains("?") ) {
				url.append("?");
				for( String key : request.getParameterMap().keySet() ) {
					if( key.equals("redirect") ) continue;
					url.append(key).append("=").append( request.getParameter(key) ).append("&");
				}
				url.deleteCharAt( url.length()-1 );
			}
			session.setAttribute("savedURL", url.toString());
		}
		
		return "default/member/login";
	}
}
