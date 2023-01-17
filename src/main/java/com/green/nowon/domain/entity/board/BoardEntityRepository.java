package com.green.nowon.domain.entity.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.board.BoardEntity;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long>{

	List<BoardEntity> findAllByOrderByBnoDesc();

	Page<BoardEntity> findAllByOrderByBnoDesc(Pageable pageable);
	
	List<BoardEntity> findByTitleContaining(String keyword); //검색
	
	//조회수 쿼리
	@Modifying
	@Query("update BoardEntity b set b.readCount = b.readCount +1 where b.bno = :bno")
	int updateReadCount(@Param("bno")Long bno);
}
