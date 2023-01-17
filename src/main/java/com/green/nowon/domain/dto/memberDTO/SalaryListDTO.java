package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.PositionEntity;
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
	
	private PositionEntity pno;
	private String pname;
	
	private int normalSalary;
	
	public SalaryListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		
		
		if(e.getPno()!=null) {	
			this.pname=e.getPno().getPName();
			this.normalSalary=e.getPno().getNormalSalary();
		}else{
			this.pname="없음";
			this.normalSalary=0;
		}
		
		if(e.defImg()!=null) {
			this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		}

	}	

}