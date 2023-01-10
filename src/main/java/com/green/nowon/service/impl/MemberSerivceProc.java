package com.green.nowon.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberDetailDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.MemberService;



@Service
public class MemberSerivceProc implements MemberService {

	@Autowired
	private MemberEntityRepository memberRepo;

	@Autowired
	private AddressEntityRepsoitory addressRepo;

	@Autowired
	private PasswordEncoder pe;

	@Override
	public void save(MemberInsertDTO mdto, AddressInsertDTO adto) {
		memberRepo.save(mdto.signin(pe).addRole(MyRole.USER)// .addRole(MyRole.ADMIN)
		);
		String id = mdto.getId();
		addressRepo.save(adto.signin().member(memberRepo.findById(id)));
	}

	@Transactional
	@Override
	public void detail(long mno, Model model, Model model2) {
		model.addAttribute("detail", memberRepo.findById(mno).map(MemberDetailDTO::new).orElseThrow());
		model2.addAttribute("aDetail", addressRepo.findByMember_mno(mno).map(AddressDetailDTO::new).orElseThrow());
	}

}
