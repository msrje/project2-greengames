package com.green.nowon.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.dto.memberDTO.MemberDetailDTO;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntityRepository;

@Service
public class MyPageServiceProcess implements MyPageService{

	
	@Autowired
	private MemberEntityRepository mRepo;
	@Autowired
	private AddressEntityRepsoitory aRepo;
	
	
	


	@Override
	public void info(long mno, Model model,Model model2) {
		model.addAttribute("detail",mRepo.findById(mno)
				.map(MemberDetailDTO::new).orElseThrow()
				);
		model2.addAttribute("mpageaddr",aRepo.findByMember_mno(mno)
				.map(AddressDetailDTO::new)
				.orElseThrow());
	}


		
	

}
