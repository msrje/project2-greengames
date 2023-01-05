package com.green.nowon.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.mypage.MyPageService;

@Controller
public class myPageController {
	
	@Autowired
	private MyPageService service;
	
	@GetMapping("/mypage")
	public String myPageInfo() {
		return"mypage/mypage";
	}
	
	@GetMapping("/mypage/address/base")
	public String baseAddr(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		service.myPageBaseAddr(userDetails.getId(),model);
		return "mypage/mypage-baseaddr";
	}
	
}
