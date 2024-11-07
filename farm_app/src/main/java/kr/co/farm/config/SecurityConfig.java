package kr.co.farm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //설정파일로 등록(컨테이너에 의해 관리되는 빈을 정의)
@EnableWebSecurity // 시큐리티 활성화(필터가 적용)
public class SecurityConfig {
	
	
	//비밀번호 암호화
	@Bean // 메서드가 반환하는 객체를 컨테이너에 빈으로 등록
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//작물가이드(guide), 주간농사정보(weekInfo), 농업기술동영상(video) : 모든 접근허용
		// 게시판, 공지글(목록,정보) : 모든 접근 허용 | 하지만 관찰일지(목록,조회)는 사용자 권한 필요
		// 공지글(notice) : 관리자 권한 필요
		// 게시판, 관찰일지 작성 : 사용자 권한 필요 
		
		http.authorizeRequests()
			.antMatchers("/**").permitAll() //모든 요청에 대한 접근 허용
		
			
			;
		http.csrf().disable();  //사이트간 요청 위조 방지 처리- 비활성화
		return http.build();
	}
	
	
	
}
