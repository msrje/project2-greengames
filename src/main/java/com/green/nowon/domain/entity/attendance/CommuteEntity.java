package com.green.nowon.domain.entity.attendance;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Gg_Commute")
@Entity
public class CommuteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cno;
	
	@CreationTimestamp//출근
	private LocalDateTime gTime;
	
	@UpdateTimestamp//퇴근
	private LocalDateTime oTime;

	@Column(nullable = false)
	private LocalDate today;
	
	@Column(nullable = false)
	private String cType;
	
	@JoinColumn(name = "mno")
	@ManyToOne
	private MemberEntity member;
	
	public CommuteEntity fkSaver(MemberEntity member) {
		this.member=member;
		return this;
	}

	
}
