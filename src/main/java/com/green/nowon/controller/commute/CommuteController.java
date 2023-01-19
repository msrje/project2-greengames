package com.green.nowon.controller.commute;

import java.awt.print.Pageable;
import java.security.Principal;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.security.MyUserDetails;
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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd일 a h시 mm분");
		String today = commuteService.findGoTime(mno).get().getGTime().format(formatter);
		System.err.println(commuteService.findGoTime(mno).get().getGTime().toLocalTime());
		
		
		return today;
	}
	/**
	 * 
	 * @param mno	: ${#authorize.principal.mno}
	 * @return 업데이트한 시간을 나타내기 위한 페이지
	 */
	@ResponseBody
	@PostMapping("/admin/commute/off/{mno}")
	public String offTime(@PathVariable long mno,CommuteInsertDTO dto, CommuteUpdateDTO udto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd일 a h시 mm분");
		commuteService.save(mno,dto,udto);//update기능도 포함되어 있습니다.
		String todayUpdateDate = commuteService.findGoTime(mno).get().getOTime().format(formatter);
		return todayUpdateDate;
	}
	
	@GetMapping("/member/commute/list")
	public String commuteList(Principal principal,Model model) {
		long mno = commuteService.MemberMno(principal);
		commuteService.showListTime(mno,model,0);
		return "/member/commuteList";
	}
	
	@GetMapping("/member/commute/list/?&page={page}")
	public String commuteListPage(@PathVariable int page, Principal principal,Model model) {
		long mno = commuteService.MemberMno(principal);
		commuteService.showListTime(mno,model,page-1);
		return "/member/commuteList";
	}
	
}
