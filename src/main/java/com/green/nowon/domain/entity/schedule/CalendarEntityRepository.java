package com.green.nowon.domain.entity.schedule;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.dto.schedule.calendarDTO;

@Repository
public interface CalendarEntityRepository extends JpaRepository<CalendarEntity, Long>{

	// 캘린더 insert
	void save(calendarDTO dto);

}
