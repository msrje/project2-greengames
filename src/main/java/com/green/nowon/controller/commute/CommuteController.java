package com.green.nowon.controller.commute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.service.attendance.CommuteService;

@Controller
public class CommuteController {

	@Autowired
	private CommuteService commuteService;
	
	
	@GetMapping("/admin/commute")
	public String goTimepage() {
		return "/admin/commute";
	}
	/**
	 * 
	 * @param mno	: ${#authorize.principal.mno}
	 * @param dto	: commute 저장을 위한 dto
	 * @return 출근 시간과 퇴근시간을 나타내기 위한 페이지
	 */
	@ResponseBody
	@PostMapping("/admin/commute/go/{mno}")
	public String goTime(@PathVariable long mno, CommuteInsertDTO dto, CommuteUpdateDTO udto) {
		commuteService.save(mno,dto,udto);
		String today = commuteService.findGoTime(mno).get().getGTime().toString();
//		System.err.println(commuteService.findGoTime(mno).get().getGTime());
		return today;
	}
	
	@ResponseBody
	@PostMapping("/admin/commute/off/{mno}")
	public String offTime(@PathVariable long mno) {
		return "/admin/commute";
	}
}
