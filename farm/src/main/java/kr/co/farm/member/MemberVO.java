package kr.co.farm.member;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberVO {
	private String name, userid, userpw, gender, email, birth
					, phone, post, address1, address2, social, role;
}
