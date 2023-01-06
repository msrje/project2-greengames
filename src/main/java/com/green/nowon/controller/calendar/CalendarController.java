package com.green.nowon.controller.calendar;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarController {
	
	//캘린더 페이지로 가기
	@GetMapping("/schedule/calendar")
	public String calendar() {
		return "/admin/calendar";
	}
	
	//일정 작성 
	@ResponseBody
	@PostMapping("/schedule/calendar")
	public Map<String, Object> calendarSave(@RequestParam Map<String, Object> param ) {
		Map<String, Object> cal = new HashMap<String, Object>();
		cal.put("title", "제목임다");
		System.out.println(param.get("title"));
		return cal; 
	}
}

	