package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class DepartmentMemberListDTO {
	
	private long mno;
	private String id;
	private String name;
	private String pass;
	private String phone;
	
	private String profileUrl;
	
	//private String pName;
	
	public DepartmentMemberListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name=e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		if(e.defImg()!=null) {
			this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		}
	}
	public DepartmentMemberListDTO(DepartmentMemberEntity e) {
		this(e.getMember());
	}
}
