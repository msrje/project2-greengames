package com.green.nowon.domain.entity.approval;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalEntityRepository extends JpaRepository<ApprovalEntity, Long>{

	Collection<ApprovalEntity> findByMno(long mno);

	Collection<ApprovalEntity> findByMno_Mno(long mno);

	

}
