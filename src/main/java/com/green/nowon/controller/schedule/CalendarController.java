package com.green.nowon.controller.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.schedule.CalendarDTO;
import com.green.nowon.service.CalendarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	@Autowired
	CalendarService cService;


	// 캘린더 페이지로 가기
	@GetMapping("/schedule/calendar")
	public String calendar() {
		return "/schedule/calendar";
	}

	// 캘린더 select
	@ResponseBody
	@GetMapping("/schedule/calendar/{userMno}")
	public List<CalendarDTO> calendarSelect(@PathVariable long userMno) {
		System.err.println("CalendarController userMno>>>>>" + userMno);
		return cService.getList(userMno);
	}

	// 캘린더 insert 
	@ResponseBody
	@PostMapping("/schedule/calendar/{userMno}")
	public String calendarInsert(@PathVariable long userMno, @RequestBody CalendarDTO dto) {
		System.err.println("CalendarController userMno>>>>" + userMno);
		System.err.println("controller DTO>>>>>" + dto);
		cService.save(userMno, dto);
		return "/schedule/calendar";
	}

	// 캘린더 Delete
	@ResponseBody
	@DeleteMapping("/schedule/calendar/{userMno}")
	public String delete(@PathVariable long userMno, @RequestBody List<Map<String, Object>> param) {
		System.err.println("CalendarController 삭제>>>>" + "작동");
		System.err.println("CalendarController param>>>>"+param.get(0).get("cno"));
		long cno = Integer.parseInt((String.valueOf(param.get(0).get("cno"))));
		System.err.println("CalendarController cno>>>"+cno);
		cService.delete(cno);
		return "redirect:/schedule/calendar";
	}
	
	//캘린더 Update
}
