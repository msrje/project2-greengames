package com.green.nowon.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntityRepository2;

@Service
public class MyPageServiceProcess implements MyPageService{

	@Autowired
	private MemberEntityRepository2 mRepo;
	@Autowired
	private AddressEntityRepsoitory aRepo;
	
	@Override
	public void myPageBaseAddr(String id, Model model) {
		model.addAttribute("mpageaddr",aRepo.findByBaseAndMemberId(true,id)
				.map(AddressDetailDTO::new).orElseThrow()
				);
	}

}
