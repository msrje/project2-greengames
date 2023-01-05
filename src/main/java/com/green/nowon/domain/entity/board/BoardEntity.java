package com.green.nowon.domain.entity.board;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_board_seq",
		sequenceName = "gg_board_seq", initialValue = 1, allocationSize = 1)
@Table(name = "gg_board")
@Entity
public class BoardEntity extends BaseDateTimeColumns{

	@Id
	@GeneratedValue(generator = "gg_gen_board_seq", strategy = GenerationType.SEQUENCE)
	private long bno;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	private int readCount;
	private String cate;
	//작성자 - MemeberEntity
	
	
	@JoinColumn(name = "mno")
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;//작성자

	//편의메서드
	public BoardEntity update(BoardUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;

	}
}
