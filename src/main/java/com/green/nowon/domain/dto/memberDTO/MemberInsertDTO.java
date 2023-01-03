package com.green.nowon.domain.dto.memberDTO;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.security.MyRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInsertDTO {
	
	private String email;
	private String pass;
	private String name;
	private String phone;
	
	//비밀번호
	public MemberEntity signin(PasswordEncoder pe) {
		return MemberEntity.builder()
				.email(email)
				.name(name)
				.pass(pe.encode(pass))//비밀번호 암호화
				.phone(phone)
				.build();
	}
	
}
