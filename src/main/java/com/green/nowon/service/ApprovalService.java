package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;

public interface ApprovalService {

	void save(ApprovalSaveDTO dto);

	void list(Model model);

	void detail(long ano, Model model, ApprovalListDTO dto);

}
