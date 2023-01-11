package com.green.nowon.service;

import java.text.ParseException;
import java.util.Map;

import com.green.nowon.domain.dto.schedule.calendarDTO;

public interface CalendarService {

	calendarDTO getListAll();
	// 캘린더 insert
	void save(calendarDTO dto);

}
