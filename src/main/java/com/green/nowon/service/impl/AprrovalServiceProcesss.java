package com.green.nowon.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;
import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.approval.ApprovalEntityRepository;
import com.green.nowon.service.ApprovalService;

@Service
public class AprrovalServiceProcesss implements ApprovalService{

	@Autowired
	ApprovalEntityRepository repo;
	
	@Override
	public void save(ApprovalSaveDTO dto) {
		repo.save(ApprovalEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
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
	public void detail(long ano, Model model, ApprovalListDTO dto) {
		model.addAttribute("list", repo.findById(ano));
		
	}




}
