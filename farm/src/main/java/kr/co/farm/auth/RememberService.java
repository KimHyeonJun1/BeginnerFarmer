package kr.co.farm.auth;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import kr.co.farm.remember.RememberMapper;
import kr.co.farm.remember.RememberVO;

public class RememberService extends AbstractRememberMeServices {
    private RememberMapper mapper;
    private SecureRandom random;

    // 생성자: 쿠키 이름, 사용자 서비스, RememberMapper를 초기화
    public RememberService(String key, LoginUserService userService, RememberMapper mapper) {
        super(key, userService);
        this.mapper = mapper;
        random = new SecureRandom();
        // 쿠키 이름을 설정
        super.setCookieName(key);
    }

    // 토큰 생성을 위한 메서드
    private String generateToken() {
        byte[] token = new byte[16];
        random.nextBytes(token);
        return Base64.getEncoder().encodeToString(token); // Base64로 인코딩하여 반환
    }

    // 로그아웃 시 호출되는 메서드
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 로그인 유지 해제 및 DB 정보 삭제
        String cookie = super.extractRememberMeCookie(request);
        if (cookie != null) {
            String tokens[] = super.decodeCookie(cookie);
            mapper.deleteRemember(tokens[0]); // DB에서 해당 series 정보 삭제
        }

        super.logout(request, response, authentication); // 쿠키 삭제
    }

    // 로그인 성공 시 호출되는 메서드
    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
                                   Authentication auth) {
        String username = auth.getName(); // 사용자 ID
        // 쿠키 저장
        String series = generateToken(); // series 생성
        String token = generateToken(); // token 생성

        // DB에 저장할 RememberVO 생성
        RememberVO vo = new RememberVO(username, series, token, new Date());
        mapper.registerRemember(vo); // DB에 RememberVO 저장

        // 브라우저에 쿠키 저장
        String[] cookie = {series, token};
        super.setCookie(cookie, getTokenValiditySeconds(), request, response);
    }

    // 자동 로그인 쿠키를 처리하는 메서드
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
                                                  HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        if (cookieTokens.length != 2) {
            throw new RememberMeAuthenticationException("잘못된 쿠키");
        }
        String series = cookieTokens[0];
        String token = cookieTokens[1];

        // DB에서 정보 확인
        RememberVO vo = mapper.getOneRemember(series);
        if (vo == null) {
        	//DB에 없는 정보인 쿠키는 삭제
            super.cancelCookie(request, response); // 쿠키 삭제
            throw new RememberMeAuthenticationException("DB에 쿠키 정보 없음");
        } else if (!token.equals(vo.getToken())) {
        	//DB의 series 는 일치하나 token 이 같지 않은 경우 쿠키가 도용됨
            super.cancelCookie(request, response); // 쿠키 삭제
            mapper.deleteRemember(series); // DB에서 series 삭제
            throw new CookieTheftException("쿠키가 도용됨");
        } else if (vo.getLast_used().getTime() + getTokenValiditySeconds() * 1000L < System.currentTimeMillis()) {
        	//유효기간이 초과 된 경우 DB정보 삭제
            super.cancelCookie(request, response); // 쿠키 삭제
            mapper.deleteRemember(series); // DB에서 series 삭제
            throw new RememberMeAuthenticationException("쿠키 유효기간 만료됨");
        }

        // 자동 로그인되면 token 값 갱신
        vo.setToken(generateToken()); // 새로운 token 생성
        vo.setLast_used(new Date()); // 마지막 사용 시간 갱신
        mapper.updateRemember(vo); // 갱신된 정보 DB에 저장

        // 브라우저에 쿠키 저장
        String[] cookie = {series, vo.getToken()};
        super.setCookie(cookie, getTokenValiditySeconds(), request, response);

        // 반환할 정보는 UserDetails(LoginUser)
        LoginUser user = (LoginUser) super.getUserDetailsService().loadUserByUsername(vo.getUsername());
        return user;
    }
}
