package kr.co.farm.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	MemberVO getOneMember(String userid);    //회원정보 조회
	
	int updateMember(MemberVO vo);           //회원정보 변경저장(마이페이지)
	int deleteMember(String userid);         //회원탈퇴시 삭제
	
}
