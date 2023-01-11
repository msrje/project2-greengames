package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;

import lombok.Getter;

@Getter
public class GenBoardDetailDTO {
	
	private long bno;
	private String title;
	private String content;
	private int readCount;
	private String writerName;
	private String writerId;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private LocalDate toDay;
	
	public GenBoardDetailDTO(GeneralBoardEntity ent) {
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.content = ent.getContent();
		this.readCount = ent.getReadCount();
		this.writerName =ent.getMember().getName();
		this.writerId =ent.getMember().getId();
		this.createdDate = ent.getCreatedDate();
		this.updatedDate = ent.getUpdatedDate();
		toDay=LocalDate.now();
	}
	
	

}
