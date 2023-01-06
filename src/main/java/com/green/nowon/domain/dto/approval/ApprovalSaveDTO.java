package com.green.nowon.domain.dto.approval;

import java.time.LocalDate;

import lombok.Data;


@Data
public class ApprovalSaveDTO {

	private String title;
	
	private String content;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
}
