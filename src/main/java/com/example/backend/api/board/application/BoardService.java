package com.example.backend.api.board.application;

import com.example.backend.api.board.domain.*;
import com.example.backend.api.board.dto.BoardResponse;
import com.example.backend.api.board.dto.CategoryResponse;
import com.example.backend.api.board.dto.RegisterBoardRequest;
import com.example.backend.api.infra.BaseEntity;
import com.example.backend.api.member.domain.Member;
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

        Map<Long, List<BoardTag>> boardTagMap = boardTagRepository.findByBoard_IdIn(boardIds)
                .stream()
                .collect(Collectors.groupingBy(it -> it.getBoard().getId()));

        Map<Long, List<Item>> itemMap = itemRepository.findByBoard_IdIn(boardIds)
                .stream()
                .collect(Collectors.groupingBy(it -> it.getBoard().getId()));

        return boards.map(it -> new BoardResponse(it, boardTagMap.get(it.getId()), itemMap.get(it.getId())));
    }

    public BoardResponse findBoardById(Long boardId) {
        Board board = boardRepository.findByIdWithCheck(boardId);

        List<BoardTag> boardTags = boardTagRepository.findByBoard_Id(board.getId());

        List<Item> items = itemRepository.findByBoard_Id(board.getId());

        return new BoardResponse(board, boardTags, items);
    }

    public List<CategoryResponse> findCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public Long registerBoard(Member member, RegisterBoardRequest registerBoardRequest) {
        Category category = categoryRepository.findByIdWithCheck(registerBoardRequest.getCategoryId());

        Board board = registerBoardRequest.toBoardEntity(category, member);

        boardRepository.save(board);

        List<BoardTag> boardTags = registerBoardRequest.toBoardTagEntity(board);

        boardTagRepository.saveAll(boardTags);

        return board.getId();
    }
}
