package com.green.nowon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.schedule.calendarDTO;
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
	public void save(calendarDTO dto) {
		CalendarEntity entity=CalendarEntity.builder()
				.cTitle(dto.getCTitle()).cStartTime(dto.getCStartTime()).cEndTime(dto.getCEndTime())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.build();
		crepo.save(entity);
		
	}

	@Override
	public calendarDTO getListAll() {
		List<CalendarEntity> dto = crepo.findAll();
		System.out.println(dto);
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public void save(Map<String, Object> param) throws ParseException {
//		
//		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
//		Date ari = dt.parse((String) param.get("start"));
//		Date cherry = dt.parse((String) param.get("end"));
//		System.out.println("프로세스>>>"+param+"\n"+ari+"\n"+cherry);
//		CalendarEntity entity=CalendarEntity.builder()
//				.cTitle((String) param.get("title")).cStartTime(ari)
//				.cEndTime(cherry).member( (MemberEntity) param.get("mno"))
////				.cEndTime(cherry).member((MemberEntity)param.get("member"))
//				.build();
//		crepo.save(entity);
//		
//		
//	}
	
	

}
