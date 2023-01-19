package com.green.nowon.controller.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		return cService.getList(userMno);
	}

	// 캘린더 insert 
	@ResponseBody
	@PostMapping("/schedule/calendar/{userMno}")
	public long calendarInsert(@PathVariable long userMno, @RequestBody CalendarDTO dto, Model model) {
		long cno = cService.save(userMno, dto);
		model.addAttribute("cno", cno);
		System.out.println("cno : " + cno);
		return cno;

	}

	// 캘린더 Delete
	@ResponseBody
	@DeleteMapping("/schedule/calendar/{userMno}")
	public String delete(@PathVariable long userMno, @RequestBody List<Map<String, Object>> param) {
		long cno = Integer.parseInt((String.valueOf(param.get(0).get("cno"))));
		cService.delete(cno);
		return "redirect:/schedule/calendar";
	}
	
	//캘린더 Update
	@ResponseBody
	@PutMapping("/schedule/calendar/{userMno}")
	public String update(@PathVariable long userMno, @RequestBody CalendarDTO dto) {
		System.out.println(dto);
		long cno = dto.getCno();
		cService.update(userMno, cno, dto);
		return "/schedule/calendar";
	}
}
