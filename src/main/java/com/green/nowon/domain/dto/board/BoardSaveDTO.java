package com.green.nowon.domain.dto.board;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardSaveDTO {
	
	private String title; 
	private String content;
	private long mno;
	private String cate;
	
	//셋팅된 dto data를 Entity객체로 변환
	public BoardEntity toBoardEntity() {
		return BoardEntity.builder()
				.title(title).content(content).member(MemberEntity.builder().mno(mno).build())
				.build();
	}
	
}
