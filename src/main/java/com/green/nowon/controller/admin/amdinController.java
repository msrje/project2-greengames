package com.green.nowon.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class amdinController {
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/ggAdmin";
	}
	
}
