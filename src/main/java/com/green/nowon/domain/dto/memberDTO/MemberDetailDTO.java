package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class MemberDetailDTO {
	
	private long mno;
	private String id;
	private String name;
	private String pass;
	private String phone;
	
	private String proImgs;
	
	public MemberDetailDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		this.proImgs=proImgs;
	}
	
}
