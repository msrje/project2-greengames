package com.green.nowon.controller.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BreakController {
	
	// 캘린더 페이지로 가기
	@GetMapping("/schedule/Request-for-Vacation")
	public String breakHtml() {
		return "/schedule/break";
	}
	
}
