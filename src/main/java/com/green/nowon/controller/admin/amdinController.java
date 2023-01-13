package com.green.nowon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.attendance.CommuteService;


@Controller
public class amdinController {
	
	@Autowired
	private CommuteService commuteService;
	
	
	@GetMapping("/admin/")
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

}
