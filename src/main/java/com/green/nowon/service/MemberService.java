package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.security.MyUserDetails;

public interface MemberService {
	void save(MemberInsertDTO mdto,AddressInsertDTO adto);

	void detail(long mno, Model model ,Model model2);
	

	void list(Model model);

//	static String saveUser(MemberInsertDTO memberInsertDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}



}
