package com.example.backend.api.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {

    List<BoardTag> findByBoard_Id(Long boardId);

    List<BoardTag> findByBoard_IdIn(List<Long> boardIds);
}
