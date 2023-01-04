package com.green.nowon.domain.entity.cate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author LeeYongJu
 * 테이블명 : Gg_position_member
 * 컬럼 : pmno(직급직원번호), position(직급테이블), member(직원테이블)
 * 직급카테고리가 실질적으로 저장이 되는 부분입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Gg_position_member")
@Entity
public class PositionMemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pmno;
	
	@JoinColumn//category_cno
	@ManyToOne
	private PositionEntity position;
	
	@JoinColumn//goods_gno
	@ManyToOne
	private MemberEntity member;

}
