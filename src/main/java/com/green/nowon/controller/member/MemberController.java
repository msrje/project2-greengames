package com.green.nowon.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/mypage")
	public String myPage() {
		return"member/mypage";
	}
	@GetMapping("/list")
	public String memberList() {
		return"member/list";
	}
}
