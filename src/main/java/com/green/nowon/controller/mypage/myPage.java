package com.green.nowon.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myPage {
	
	@GetMapping("/mypage")
	public String myPageInfo() {
		return"member/mypage";
	}
	
}
