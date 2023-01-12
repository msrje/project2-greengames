package com.green.nowon.service.attendance;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;

public interface CommuteService {

	Optional<CommuteEntity> findGoTime(long mno);

	void save(long mno, CommuteInsertDTO idto, CommuteUpdateDTO udto);

	void showGTime(Model model, Long mno);

}
