package com.green.nowon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.green.nowon.service.attendance.CommuteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.MemberService;
import com.green.nowon.service.PositionService;
import com.green.nowon.service.impl.PositionServiceProcess;



@Controller
public class amdinController {
	
	@Autowired
	private CommuteService commuteService;

  @Autowired
	PositionService service;

	@GetMapping("/admin")
	public String admin() {
		return "admin/ggAdmin";
	}
	
	@GetMapping("/admin/{mno}")
	public String admin2(@PathVariable long mno,Model model) {
		commuteService.showGTime(mno,model);
		return "admin/ggAdmin";
	}
	
	@GetMapping("/adminlist")
	public String adminlist() {
		return "admin/admin-list";
	}
	
	@GetMapping("/admin/position")
	public String position() {
		return "/admin/position";
	}
	
	@PostMapping("/admin/position")
	public String addPosition(String name,int salary){
		System.out.println(name);
		service.save(name,salary);
		return "/admin/position";
	}

}
