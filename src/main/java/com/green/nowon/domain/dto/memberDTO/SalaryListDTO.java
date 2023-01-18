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
	
	
	double sal1y;
	double sal2y;
	double sal3y;
	double sal4y;
	double sal5y;
	
	int minSalTot;
	
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
			
			this.none=0;//신입
			this.sal1y=e.getPno().getNormalSalary()*0.05;//기본급의 3% 1년차
			this.sal2y=e.getPno().getNormalSalary()*0.10;//기본급의 6% 2년차
			this.sal3y=e.getPno().getNormalSalary()*0.15;//기본급의 9% 3년차
			this.sal4y=e.getPno().getNormalSalary()*0.20;//기본급의 12% 4년차
			this.sal5y=e.getPno().getNormalSalary()*0.25;//기본급의 15% 5년차
			
			this.minSalTot=(int) (e.getPno().getNormalSalary()-minSal);//세금이 포함된 월급
			
		}else{
			this.pname="없음";
			this.normalSalary=0;
			
			this.minSal=0;//세금
			
			this.sal1y=0;
			this.sal2y=0;
			this.sal3y=0;
			this.sal4y=0;
			this.sal5y=0;
			
			this.minSalTot=0;
		}
		
		
		if(e.defImg()!=null) {
			this.profileUrl=e.defImg().getUrl()+e.defImg().getNewName();
		}

	}	

}