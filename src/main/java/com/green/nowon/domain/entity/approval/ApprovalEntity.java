package com.green.nowon.domain.entity.approval;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "ggApproval")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ApprovalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ano;
	
	private String title;
	
	private String content;
	
	private boolean status;
	
	private long mno;
	
	private long adno;
}
