package com.green.nowon.domain.dto.commuteMember;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.attendance.CommuteEntity;

import lombok.Data;

@Data
public class CommuteMemberListDTO {
	
	private LocalDateTime gTime;
	
	private LocalDateTime oTime;
	
	private LocalDate today;
	
	private String cType;
	
	private String name;
	/*
	public CommuteMemberListDTO(CommuteEntity e) {
		this.gTime = e.getGTime();
		this.oTime = e.getOTime();
		this.today = e.getToday();
		this.cType = e.getCType();
		this.name = e.getMember().getName();
	}*/
}
