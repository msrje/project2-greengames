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
	
	
	int none;
	double minSal;
	int totSal;
	
	public SalaryListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		
		if(e.getPno()!=null) {	//직책,기본금,마이너스,플러스금액 계산
			this.pname=e.getPno().getPName();
			this.normalSalary=e.getPno().getNormalSalary();
			
			this.minSal=e.getPno().getNormalSalary()*0.16;//-금액 은 고정(ex)세금)
			this.none=0;
			
			this.totSal=(int) (e.getPno().getNormalSalary()-minSal);
			
		}else{
			this.pname="없음";
			this.normalSalary=0;
			
			this.minSal=0;//세금
			
			this.totSal=0;
		}
		
		
		if(e.defImg()!=null) {
			this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		}

	}	

}