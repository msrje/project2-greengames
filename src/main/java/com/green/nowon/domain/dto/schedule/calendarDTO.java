package com.green.nowon.domain.dto.schedule;

import java.util.Date;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity;

import lombok.Data;

@Data
public class calendarDTO {
	
	private String cTitle; 
	private Date cStartTime;
	private Date cEndTime;
	private long mno;
	
	public CalendarEntity toCalendarEntity() {
		
		return CalendarEntity.builder()
				.cTitle(cTitle).cStartTime(cStartTime).cEndTime(cEndTime).member(MemberEntity.builder().mno(mno).build())
				.build();
	}
	
}
