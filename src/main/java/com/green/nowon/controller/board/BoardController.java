package com.green.nowon.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@GetMapping("/notice-boards")
	public String board() {
		return "board/noticeList";
	}
	
	@GetMapping("/boards")
	public String secBoard() {
		return "board/boardList";
	}
	
	@GetMapping("/admin/boards-registration")
	public String boardReg() {
		return "board/adminWrite";
	}
	
	@PostMapping("/admin/boards-registration")        
	public String write(BoardSaveDTO dto) {
		service.save(dto);		
		return "redirect:/notice-boards";
	}
	
}
