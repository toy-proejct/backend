package com.example.backend.api.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByBoard_Id(Long boardId);

    List<Item> findByBoard_IdIn(List<Long> boardIds);
}
