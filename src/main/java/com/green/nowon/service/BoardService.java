package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;

public interface BoardService {

	void getListAll(int page, Model model);

	void sendDetail(long bno, Model model);
	
	void save(BoardSaveDTO dto, String name);

	void save(BoardSaveDTO dto);

	void delete(long bno);

	void update(long bno, BoardUpdateDTO dto);

	void myGetListAll(Model model);

	void getListAll02(int page, Model model);

	void sendDetail02(long bno, Model model);	
	
	void save02(GenBoardSaveDTO dto, String name);

	void save02(GenBoardSaveDTO dto);

	void delete02(long bno);

	void update02(long bno, GenBoardUpdateDTO dto);

	void myGetListAll02(Model model);

}
