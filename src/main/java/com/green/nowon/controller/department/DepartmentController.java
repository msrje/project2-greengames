package com.green.nowon.controller.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.DepartmentService;
import com.green.nowon.service.impl.DepartmentServiceProc;

@Controller
public class DepartmentController {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@GetMapping("/admin/departments/{text}")
	public boolean isPresent(@PathVariable String text) {
		return departmentService.isReg(text);
	}
	
	@PostMapping("/admin/departments")
	public String category(String[] name) {
		departmentService.save(name);
		return "admin/departmentCate/reg";
	}
}
