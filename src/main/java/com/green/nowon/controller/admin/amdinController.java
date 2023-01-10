package com.green.nowon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.DepartmentService;

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
