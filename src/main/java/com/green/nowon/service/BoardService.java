package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.domain.dto.board.BoardUpdateDTO;

public interface BoardService {

	void getListAll(int page, Model model);

	void sendDetail(long bno, Model model);
	
	void save(BoardSaveDTO dto, String name);

	void save(BoardSaveDTO dto);

	void delete(long bno);

	void update(long bno, BoardUpdateDTO dto);

	void getListAll02(Model model);

}
