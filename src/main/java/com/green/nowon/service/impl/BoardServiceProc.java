package com.green.nowon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import com.green.nowon.domain.dto.board.GenBoardDetailDTO;
import com.green.nowon.domain.dto.board.GenBoardListDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;
import com.green.nowon.domain.entity.board.BoardEntityRepository;
import com.green.nowon.domain.entity.board.GenBoardEntityRepository;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProc implements BoardService{

	private final BoardEntityRepository repository;
	private final GenBoardEntityRepository repo;
	
	
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
	
	//조회수
    @Transactional
    public int updateReadCount(Long bno) {
        return repository.updateReadCount(bno);
    }
	
	
	
	//자유게시판
	
	@Override
	public void getListAll02(int page, Model model) {
		//board list를 페이지로 전송
		int size=10;
		Sort sort=Sort.by(Direction.DESC, "bno");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<GeneralBoardEntity> result=repo.findAll(pageable);
		
		model.addAttribute("p2", result);
		model.addAttribute("list2", result.stream()
				.map(GenBoardListDTO::new)
				.collect(Collectors.toList()));
	}

	@Override
	public void sendDetail02(long bno, Model model) {
		model.addAttribute("detail2", repo.findById(bno)
				.map(GenBoardDetailDTO::new)
				.orElseThrow());
		
	}

	@Override
	public void save02(GenBoardSaveDTO dto, String name) {
	}
	
	@Override
	public void save02(GenBoardSaveDTO dto) {
		
		GeneralBoardEntity entity=GeneralBoardEntity.builder()
				.title(dto.getTitle()).content(dto.getContent())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.build();
		repo.save(entity);
		
	}

	@Override
	public void delete02(long bno) {
		repo.deleteById(bno);
	}

	@Override
	public void update02(long bno, GenBoardUpdateDTO dto) {
		Optional<GeneralBoardEntity> result= repo.findById(bno);
		
		//존재하면 수정
		if(result.isPresent()) {
			GeneralBoardEntity entity=result.get();
			entity.update(dto);
			//업데이트 반영
			repo.save(entity);//이미 존재하는 Pk이면 수정됨
		}
		
	}

	
	

	//mypage에 띄우는 리스트
	@Transactional
	@Override
	public void myGetListAll(Model model) {
		
		List<BoardListDTO> result=repository.findAll().stream()
				.map(BoardListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
	}

	@Transactional
	@Override
	public void myGetListAll02(Model model) {
		List<GenBoardListDTO> result=repo.findAll().stream()
				.map(GenBoardListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("list2", result);
		
	}

}
