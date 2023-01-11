package com.green.nowon.domain.entity.attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteEntityRepository extends JpaRepository<CommuteEntity, Long>{
	void save(long mno);

	List<CommuteEntity> findAllByMember_mno(long mno);

}
