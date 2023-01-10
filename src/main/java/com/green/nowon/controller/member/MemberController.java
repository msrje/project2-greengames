package com.green.nowon.controller.member;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@PostMapping("/mypage/temp-upload")
	public Map<String, String> memberDetailImg(MultipartFile img) {
		return memberService.fileTempUpload(img);
	}
	
	@GetMapping("/mypage/{mno}")
	public String detail(@PathVariable long mno,Model model,Model model2) {
		System.err.println(">>>>>>>>>>>>>>>>" + mno);
		memberService.detail(mno,model,model2);
		return "/mypage/employee-detail";
	}
	
	@PostMapping("/mypage/{mno}/update")
	public String update() {
		return "redirect:/mypage";
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
