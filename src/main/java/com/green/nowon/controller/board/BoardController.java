package com.green.nowon.controller.board;

import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;
import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	//공지사항 게시판
	
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
	
	@ResponseBody//응답데이터를 json타입으로 리턴
	@PostMapping("/admin-notice/temp-upload")
	public Map<String,String> tempUpload(MultipartFile bimg) {
		return service.fileTempUpload(bimg);
	}
	
	
	//공지사항 상세페이지
	@GetMapping("/notice-boards/{bno}")
	public String detail(@PathVariable long bno, Model model) {
		service.updateReadCount(bno);  //조회수(레포지토리에 쿼리추가후)
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
	
	//검색
	@GetMapping("/notice-boards/search")
    public String search(String keyword, Model model) {
        service.search(keyword, model);
        return "board/noticeSearchPage";
    }
	
	
	
	//자유게시판
	
	@GetMapping("/boards")
	public String genBoard(@RequestParam(defaultValue = "1") int page , Model model) {
		service.getListAll02(page ,model);
		return "board/generalList";
	}
	
	@GetMapping("/general/boards-registration")
	public String genBoardReg() {
		return "board/generalWrite";
	}
	
	@PostMapping("/boards")        
	public String genWrite(GenBoardSaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno());
		service.save02(dto);		
		return "redirect:/boards";
	}
	
	//자유게시판 상세페이지
	@GetMapping("/boards/{bno}")
	public String genDetail(@PathVariable long bno, Model model) {
		service.genUpdateReadCount(bno);  //조회수(레포지토리에 쿼리추가후)
		service.sendDetail02(bno, model);
		return "board/generalDetail";
	}
	
	//삭제
	@DeleteMapping("/boards/{bno}")
	public String genDelete(@PathVariable long bno) {
		
		service.delete02(bno);
		return "redirect:/boards";
	}
	
	//수정
	@PutMapping("/boards/{bno}")                 //setter 있어야함.
	public String genUpdate(@PathVariable long bno, GenBoardUpdateDTO dto) {
		service.update02(bno, dto);
		return "redirect:/boards/{bno}";
	}
	
}
