package com.example.backend.api.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public interface BoardRepository extends JpaRepository<Board, Long> {

    default Board findByIdWithCheck(Long boardId) {
        return findById(boardId).orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다"));
    }
}
