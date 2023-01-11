package com.green.nowon.controller.commute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.service.attendance.CommuteService;

@Controller
public class CommuteController {

	@Autowired
	private CommuteService commuteService;
	
	
	@GetMapping("/admin/commute")
	public String goTimepage() {
		return "/admin/commute";
	}
	
	@PostMapping("/admin/commute/go/{mno}")
	public String goTime(@PathVariable long mno, CommuteInsertDTO dto) {
		
		commuteService.save(mno,dto);
		return "/admin/commute";
	}
	
	@ResponseBody
	@PostMapping("/admin/commute/off/{mno}")
	public String offTime(@PathVariable long mno) {
		return "/admin/commute";
	}
}
