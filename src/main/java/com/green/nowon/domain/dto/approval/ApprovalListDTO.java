package com.green.nowon.domain.dto.approval;

import java.sql.Date;

import com.green.nowon.domain.entity.approval.ApprovalEntity;

import lombok.Data;

@Data
public class ApprovalListDTO {
	
	private long ano;

	private String title;
	
	private String content;
	
	private Date date;
		
	private String status;
	
	
	public ApprovalListDTO(ApprovalEntity e) {
		this.date=e.getDate();
		this.ano=e.getAno();
		this.title=e.getTitle();
		this.content=e.getContent();
		this.status=e.getStatus();
		
	}
	
}
