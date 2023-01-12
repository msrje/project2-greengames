package com.green.nowon.service;

import java.util.List;

import com.green.nowon.domain.dto.schedule.CalendarDTO;

public interface CalendarService {
	// 캘린더 insert
	void save(long userMno , CalendarDTO dto);

	// 캘린더 select
	List<CalendarDTO> getList(long userMno);


}
