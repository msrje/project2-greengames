package com.green.nowon.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.BoardDetailDTO;
import com.green.nowon.domain.dto.board.BoardListDTO;
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
		//board list를 페이지로 전송
		int size=10;
		Sort sort=Sort.by(Direction.DESC, "bno");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<BoardEntity> result=repository.findAll(pageable);
		
		model.addAttribute("p", result);
		model.addAttribute("list", result.stream()
				.map(BoardListDTO::new)
				.collect(Collectors.toList()));
	}

	@Override
	public void sendDetail(long bno, Model model) {
		model.addAttribute("detail", repository.findById(bno)
				.map(BoardDetailDTO::new)
				.orElseThrow());
		
	}

	@Override
	public void save(BoardSaveDTO dto, String name) {
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
		repository.deleteById(bno);
	}

	@Override
	public void update(long bno, BoardUpdateDTO dto) {
		Optional<BoardEntity> result= repository.findById(bno);
		
		//존재하면 수정
		if(result.isPresent()) {
			BoardEntity entity=result.get();
			entity.update(dto);
			//업데이트 반영
			repository.save(entity);//이미 존재하는 Pk이면 수정됨
		}
		
	}

	@Override
	public void updateProc(long bno, BoardUpdateDTO dto) {
		
		
	}

}
