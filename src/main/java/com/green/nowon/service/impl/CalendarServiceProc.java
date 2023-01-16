package com.green.nowon.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.schedule.CalendarDTO;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntityRepository;
import com.green.nowon.service.CalendarService;

@Service
public class CalendarServiceProc implements CalendarService {

	//캘린더엔티티
	@Autowired
	private CalendarEntityRepository crepo;

	// 캘린더 insert
	@Override
	public void save(long userMno, CalendarDTO dto) {
		CalendarEntity entity=CalendarEntity.builder()
				.cTitle(dto.getCTitle()).cStartTime(dto.getCStartTime()).cEndTime(dto.getCEndTime())
				.member(MemberEntity.builder().mno(userMno).build())
				.build();
		crepo.save(entity);
		
	}
	
	// 캘린더 select
	@Override
	public List<CalendarDTO> getList(long userMno) {
		return crepo.findAllByMember_mno(userMno).stream()
				.map(CalendarDTO::new)
				.collect(Collectors.toList());
	}

	// 캘린더 Delete
	@Override
	public void delete(long cno) {
		crepo.deleteById(cno);
		
	}
	
	

}
