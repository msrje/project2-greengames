package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class SalaryListDTO {
	
	private long mno;
	private String id;
	private String name;
	private String pass;
	private String phone;
	
	private String profileUrl;
	
	public SalaryListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		if(e.defImg()!=null) {
			this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		}
		
		
	}
	
}
