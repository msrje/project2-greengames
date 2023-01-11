package com.green.nowon.service.attendance;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;

public interface CommuteService {

	void save(long mno, CommuteInsertDTO dto);


}
