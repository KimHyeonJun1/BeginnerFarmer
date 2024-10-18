package kr.co.farm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.farm.auth.LoginSuccess;
import kr.co.farm.auth.LogoutSuccess;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //설정파일로 등록(컨테이너에 의해 관리되는 빈을 정의)
@EnableWebSecurity // 시큐리티 활성화(필터가 적용)
public class SecurityConfig {
	private final LoginSuccess loginSuccess;
	private final LogoutSuccess logoutSuccess;
	
	//비밀번호 암호화
	@Bean // 메서드가 반환하는 객체를 컨테이너에 빈으로 등록
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//보안 필터 체인 - 인증,인가를 위한 필터들의 모음
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll() //모든 요청에 대한 접근 허용
		
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
				
				;
				
			
		
		
		http.csrf().disable();  //사이트간 요청 위조 방지 처리- 비활성화
		
		
		return http.build();
	}
	
	



}
