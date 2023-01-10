package com.green.nowon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.MemberService;


@Controller
public class amdinController {
	
	

	@GetMapping("/admin")
	public String admin() {
		
		return "admin/ggAdmin";
	}
	
	@GetMapping("/adminlist")
	public String adminlist() {
		return "admin/admin-list";
	}

}
