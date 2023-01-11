package com.green.nowon.domain.entity.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteEntityRepository extends JpaRepository<CommuteEntity, Long>{
	void save(long mno);
}
