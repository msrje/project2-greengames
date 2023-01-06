package com.green.nowon.controller.member;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.MemberService;
import com.green.nowon.service.impl.MemberSerivceProc;

@Controller
public class MemberController {
	
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@PostMapping("/member/temp-upload")
	public void memberDetailImg() {
	}
	
	@GetMapping("/mypage/{mno}")
	public String detail(@PathVariable long mno,Model model,Model model2) {
		memberService.detail(mno,model,model2);
		return "/mypage/employeeUpdate";
	}
	
	@GetMapping("/update")
	public String update() {
		return "/admin/employeeUpdate";
	}
	@GetMapping("/list")
	public String memberList() {
		return"member/employee-list";
	}
	
}
