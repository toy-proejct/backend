package com.example.backend.api.board.application;

import com.example.backend.api.board.domain.*;
import com.example.backend.api.board.dto.BoardResponse;
import com.example.backend.api.infra.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class BoardService {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final BoardTagRepository boardTagRepository;
    private final ItemRepository itemRepository;

    public BoardService(CategoryRepository categoryRepository, BoardRepository boardRepository, BoardTagRepository boardTagRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
        this.boardTagRepository = boardTagRepository;
        this.itemRepository = itemRepository;
    }

    public Page<BoardResponse> findBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        List<Long> boardIds = boards.getContent()
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        Map<Long, List<BoardTag>> boardTagMap = boardTagRepository.findAllById(boardIds)
                .stream()
                .collect(Collectors.groupingBy(it -> it.getBoard().getId()));


        return boards.map(it -> new BoardResponse(it, boardTagMap.get(it.getId())));
    }
}
