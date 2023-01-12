package com.green.nowon.domain.entity.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.board.BoardEntity;

@Repository
public interface GenBoardEntityRepository extends JpaRepository<GeneralBoardEntity, Long>{

	List<GeneralBoardEntity> findAllByOrderByBnoDesc();

	Page<GeneralBoardEntity> findAllByOrderByBnoDesc(Pageable pageable);
}
