package kr.co.farm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.farm.auth.LoginSuccess;
import kr.co.farm.auth.LoginUserService;
import kr.co.farm.auth.LogoutSuccess;
import kr.co.farm.auth.RememberService;
import kr.co.farm.auth.SocialUserService;
import kr.co.farm.remember.RememberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //설정파일로 등록(컨테이너에 의해 관리되는 빈을 정의)
@EnableWebSecurity // 시큐리티 활성화(필터가 적용)
public class SecurityConfig {
	
	private final LoginSuccess loginSuccess;
	private final LogoutSuccess logoutSuccess;
	private final SocialUserService socialService;
	private final RememberMapper mapper;
	private final LoginUserService userService;
	
	@Bean
	RememberService rememberMe() {
		return new RememberService( rememberKey, userService, mapper);
	}
	
	@Value("${rememberKey}") private String rememberKey;
	
	//비밀번호 암호화
	@Bean // 메서드가 반환하는 객체를 컨테이너에 빈으로 등록
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//보안 필터 체인 - 인증,인가를 위한 필터들의 모음
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//작물가이드(guide), 주간농사정보(weekInfo), 농업기술동영상(video) : 모든 접근허용
		// 게시판, 공지글(목록,정보) : 모든 접근 허용 | 하지만 관찰일지(목록,조회)는 사용자 권한 필요
		// 공지글(notice) : 관리자 권한 필요
		// 게시판, 관찰일지 작성 : 사용자 권한 필요 
		
		http.authorizeRequests()
//			.antMatchers("/**").permitAll() //모든 요청에 대한 접근 허용
			.antMatchers("/guide/**", "/weekInfo/**", "/video/**").permitAll()
			.antMatchers("/board/list", "/board/info","/notice/list", "/notice/info").permitAll()
			.antMatchers("/notice/register").hasAnyAuthority("ADMIN")
			.antMatchers("/**/register").hasAnyAuthority("USER")
	
		
			//스프링시큐리티 로그인 적용하기
			.and()
			.formLogin()
				.loginPage("/member/login") // 로그인 페이지 URL
				.usernameParameter("userid") 
				.passwordParameter("userpw")
				.loginProcessingUrl("/member/farmLogin") // 로그인 요청을 처리할 URL 지정
				.failureUrl("/member/login/fail") // 로그인 실패 시 리다이렉트할 URL 지정
				.successHandler(loginSuccess) // 로그인 성공시 실행될 커스텀 성공 핸들러 설정
			
			// 로그아웃
			.and()
			.logout()
				.logoutUrl("/member/logout") // 로그아웃 페이지 URL 
				.invalidateHttpSession(true) // 로그아웃 시 세션 무효화
				.logoutSuccessHandler(logoutSuccess) // 로그아웃 성공시 실행될 커스텀 성공 핸들러 설정
				
			//로그인 상태 유지
			.and()
			.rememberMe()
				.key(rememberKey) // 토큰 암호화 고유 키
				.tokenValiditySeconds(60*60*24*30) // 유효기간 (쿠키 저장 한 달)
				.rememberMeServices(rememberMe() ) // 쿠키 생성 및 검증과정 제어
				
			//소셜로그인
			.and()
			.oauth2Login()
				.successHandler(loginSuccess)
				.loginPage("/member/login")
				.userInfoEndpoint() //로그인성공 후 사용자정보 가져오기위한 설정
				.userService(socialService)
			
			;
		http.csrf().disable();  //사이트간 요청 위조 방지 처리- 비활성화
		return http.build();
	}
	
	
	



}
