package com.green.nowon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.service.MemberService;

@Service
public class MemberServiceProcess implements MemberService {

	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Autowired
	private AddressEntityRepsoitory addressRepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public void save(MemberInsertDTO mdto, AddressInsertDTO adto) {
		memberRepo.save(mdto.signin(pe).addRole(MyRole.USER));
	    String id = mdto.getId();
		addressRepo.save(adto.signin().member(memberRepo.findById(id)));
	}

}
