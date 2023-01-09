package com.green.nowon.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.MemberService;

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
	
	@GetMapping("/employee")//카테고리 리스트롤 사용할 예정
	public String employeeList() {
		return"member/employee-list";
	}
	
	@GetMapping("/salary")//급여관리 페이지에서 보이는 멤버리스트
	public String memberList(Model model) {
		memberService.list(model);
		return"member/salary-list";
	}
	
}
