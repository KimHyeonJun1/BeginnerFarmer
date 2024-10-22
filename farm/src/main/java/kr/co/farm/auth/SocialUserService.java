package kr.co.farm.auth;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.farm.common.CommonUtility;
import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class SocialUserService extends DefaultOAuth2UserService {
	private final CommonUtility common;
	private final MemberMapper member;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth =  super.loadUser(userRequest);
		Map<String, Object > response= oauth.getAttributes();
		
		//소셜 종류에 따라 사용자정보의 형태가 다름
		//소셜사용자 정보를 우리 DB에 저장해야 함
		String social = userRequest.getClientRegistration().getRegistrationId();
		MemberVO vo = null;
		if( social.equals("naver") ) {
			vo = setNaverUser( new JSONObject(response) );
		}else if (social.equals("kakao")) {
			vo = setKaKaoUser( new JSONObject(response) );
			
		}
		
		// 네이버로그인이 처음이면 insert 아니면 update
		if (member.getOneMember(vo.getUserid()) == null) {
			member.registerMember(vo);
		} else {
			member.updateMember(vo);
		}
		
	

		return new LoginUser(vo);
	}

	private MemberVO setKaKaoUser(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	private MemberVO setNaverUser(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
	
	
}
