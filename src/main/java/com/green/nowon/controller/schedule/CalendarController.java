package com.green.nowon.controller.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
public class CalendarController {

	// 캘린더 페이지로 가기
	@GetMapping("/schedule/calendar")
	public String calendar() {
		return "/schedule/calendar";
	}
	
	
}
