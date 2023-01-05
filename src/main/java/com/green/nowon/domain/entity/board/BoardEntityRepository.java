package com.green.nowon.domain.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.board.BoardEntity;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long>{

}
