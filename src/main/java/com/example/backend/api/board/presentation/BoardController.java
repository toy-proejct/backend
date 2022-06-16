package com.example.backend.api.board.presentation;

import com.example.backend.api.board.application.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<?> findBoards(Pageable pageable) {
        return ResponseEntity.ok(boardService.findBoards(pageable));
    }
}
