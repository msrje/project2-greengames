package com.green.nowon.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.mypage.MyPageService;

@Controller
public class myPageController {
	
	@Autowired
	private MyPageService service;
	
	@GetMapping("/mypage/info/{mno}")
	public String myPageInfo(@PathVariable long mno,Model model,Model model2) {
		service.info(mno,model,model2);
		return"mypage/mypage";
	}
	

	
	
}
