package kr.co.farm;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.co.farm.member.MemberMapper;
import kr.co.farm.member.MemberVO;
import kr.co.farm.test.TestMapper;

//@SpringBootTest
class FarmApplicationTests {

    @Autowired
    private MemberMapper memberMapper; // MemberMapper 주입

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화기 주입
    
  //로그인
//  	@Test
  	void login() {
  		Scanner scan = new Scanner(System.in);
  		System.out.print("아이디: ");
  		String userid = scan.next();
  		
  		System.out.print("비밀번호: ");
  		String userpw = scan.next();
  		
  		scan.close();
  		
  		MemberVO vo = memberMapper.getOneMember(userid);
  		if(vo==null) {
  			System.out.println("아이디 불일치");
  		}else {
  			//입력비번과 암호화된 DB의 비번 일치여부 확인
  			boolean match = passwordEncoder.matches(userpw, vo.getUserpw());
  			if(match) {
  				System.out.println(vo.getName() + "회원으로 로그인됨");
  			} else {
  				System.out.println("비번 불일치");
  			}
  		}
  	}
  	

    // 회원가입(회원정보 저장)
//    @Test
    void join() {
        Scanner scan = new Scanner(System.in);
        
        MemberVO vo = new MemberVO();
        
        System.out.println("이름: ");
        vo.setName(scan.next());
        
        System.out.println("아이디: ");
        vo.setUserid(scan.next());
        
        System.out.println("비번: ");
        // 입력한 비밀번호를 암호화하여 저장
        vo.setUserpw(passwordEncoder.encode(scan.next()));
        
        System.out.println("이메일: ");
        vo.setEmail(scan.next());
        
        System.out.println("관리자?(Y/N): ");
        
        vo.setRole(scan.next().toUpperCase().equals("Y") ? "ADMIN" : "USER");
        
        scan.close();
        
        // 회원가입 호출
        int dml = memberMapper.registerMemberForTest(vo); // MemberMapper의 회원가입 메서드 호출
        System.out.println(dml == 1 ? "가입성공" : "가입실패");
        
    }
    
    @Autowired  private TestMapper test;
//    @Test
    void test() {
    	String today = test.today();
    	System.out.println( today ); 
    }
    
}
