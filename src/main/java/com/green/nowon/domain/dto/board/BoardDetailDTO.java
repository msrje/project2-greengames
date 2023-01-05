package com.green.nowon.domain.dto.board;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.BoardEntity;

import lombok.Getter;

@Getter
public class BoardDetailDTO {
	
	private long bno;
	private String title;
	private String content;
	private int readCount;
	private String writerName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public BoardDetailDTO(BoardEntity ent) {
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.content = ent.getContent();
		this.readCount = ent.getReadCount();
		this.writerName =ent.getMember().getName();
		this.createdDate = ent.getCreatedDate();
		this.updatedDate = ent.getUpdatedDate();
	}
	
	

}
