package com.green.nowon.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;
import com.green.nowon.domain.dto.attendance.AttendanceSaveDTO;
import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.approval.ApprovalEntityRepository;
import com.green.nowon.domain.entity.attendance.AttendanceEntity;
import com.green.nowon.domain.entity.attendance.AttendanceEntityRepository;
import com.green.nowon.service.ApprovalService;

@Service
public class AprrovalServiceProcesss implements ApprovalService{

	@Autowired
	ApprovalEntityRepository repo;
	
	@Autowired
	AttendanceEntityRepository adrepo;
	
	@Override
	public void save(ApprovalSaveDTO dto) {
		repo.save(ApprovalEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.date(dto.getDate())
				.build());
		
	}

	@Override
	public void list(Model model) {
		model.addAttribute("list", repo.findAll()
				.stream()
				.map(ApprovalListDTO::new)
				.collect(Collectors.toList()));		
	}

	@Override
	public void detail(long ano, Model model) {
		model.addAttribute("list", repo.findById(ano).map(ApprovalListDTO::new)
				.orElseThrow());
		
	}

	@Override
	public void ok(long ano) {
			Optional<ApprovalEntity> approval=repo.findById(ano);
			
			approval.ifPresent(e -> {
				e.setStatus(true);
				repo.save(e);
			});
			
		
	}

	@Override
	public void save(AttendanceSaveDTO dto) {		
		repo.save(ApprovalEntity.builder()
				.content(dto.getContent())
				.date(dto.getDate())
				.title(dto.getAdtype())
				.adno(AttendanceEntity.builder()
						.adtype(dto.getAdtype())
						.sdate(dto.getSdate())
						.edate(dto.getEdate())
						.build())
				.build());
		
	}

	




}
