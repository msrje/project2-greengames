package com.green.nowon.controller.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	// 캘린더 페이지로 가기
	@GetMapping("/schedule/calendar")
	public String calendar() {
		return "/admin/calendar";
	}
	

	
}
