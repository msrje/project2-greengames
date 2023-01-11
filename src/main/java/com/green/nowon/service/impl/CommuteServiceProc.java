package com.green.nowon.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.attendance.CommuteEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository2;
import com.green.nowon.service.attendance.CommuteService;

@Service
public class CommuteServiceProc implements CommuteService {

	
	@Autowired
	private CommuteEntityRepository commuteRepo;

	@Autowired
	private MemberEntityRepository2 memberRepo;
	
	@Transactional
	@Override
	public void save(long mno, CommuteInsertDTO dto) {
		commuteRepo.save(dto.entity().fkSaver(memberRepo.findByMno(mno)));
	}
	
	
}
