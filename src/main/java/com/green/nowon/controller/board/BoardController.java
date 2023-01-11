package com.green.nowon.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@GetMapping("/notice-boards")
	public String board(@RequestParam(defaultValue = "1") int page , Model model) {//문자열로 파라미터 매핑--> int형 parse
		service.getListAll(page ,model);
		return "board/noticeList";
	}
	
	@GetMapping("/admin/boards-registration")
	public String boardReg() {
		return "board/adminWrite";
	}
	
	@PostMapping("/notice-boards")        
	public String write(BoardSaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno());
		service.save(dto);		
		return "redirect:/notice-boards";
	}
	
	//공지사항 상세페이지
	@GetMapping("/notice-boards/{bno}")
	public String detail(@PathVariable long bno, Model model) {
		service.sendDetail(bno, model);
		return "board/noticeDetail";
	}
	
	//삭제
	@DeleteMapping("/notice-boards/{bno}")
	public String delete(@PathVariable long bno) {
		
		service.delete(bno);
		return "redirect:/notice-boards";
	}
	
	//수정
	@PutMapping("/notice-boards/{bno}")                 //setter 있어야함.
	public String update(@PathVariable long bno, BoardUpdateDTO dto) {
		service.update(bno, dto);
		return "redirect:/notice-boards/{bno}";
	}
	
	
	
	//일반게시판
	
	@GetMapping("/boards")
	public String secBoard() {
		return "board/boardList";
	}
	
}
