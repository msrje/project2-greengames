package com.green.nowon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.entity.board.BoardEntityRepository;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProc implements BoardService{

	private final BoardEntityRepository repository;
	
	@Override
	public void getListAll(int page, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendDetail(long bno, Model model) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void save(BoardSaveDTO dto) {
		
		BoardEntity entity=BoardEntity.builder()
				.title(dto.getTitle()).content(dto.getContent())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.build();
		repository.save(entity);
		
	}

	@Override
	public void delete(long bno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long bno, BoardUpdateDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProc(long bno, BoardUpdateDTO dto) {
		// TODO Auto-generated method stub
		
	}

}
