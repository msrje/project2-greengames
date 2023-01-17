package com.green.nowon.domain.dto.commuteMember;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class CommuteMemberListDTO {
	
	private LocalDateTime gTime;
	
	private LocalDateTime oTime;
	
	private LocalDate today;
	
	private String cType;
	
	private MemberEntity member;
	
	private String name;
	
	public CommuteMemberListDTO(CommuteEntity e) {
		if(e==null) {
			this.gTime = LocalDateTime.now();
			this.oTime = LocalDateTime.now();
			this.today = LocalDate.now();
			this.cType = "첫출근";
			this.name = "첫출근";
		}else {
			System.err.println("값 있어");
			this.gTime = e.getGTime();
			this.oTime = e.getOTime();
			this.today = e.getToday();
			this.cType = e.getCType();
			this.name = e.getMember().getName();
		}
	}
}
