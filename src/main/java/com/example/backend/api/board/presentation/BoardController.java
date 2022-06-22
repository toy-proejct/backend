package com.example.backend.api.board.presentation;

import com.example.backend.api.board.application.BoardService;
import com.example.backend.api.board.dto.RegisterBoardRequest;
import com.example.backend.api.member.domain.Member;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.security.annotations.MemberClaim;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @GetMapping("/{boardId}")
    public ResponseEntity<?> findById(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.findBoardById(boardId));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> findCategories() {
        return ResponseEntity.ok(boardService.findCategories());
    }

//    @Authenticated
    @PostMapping("/register")
    public ResponseEntity<?> registerBoard(@ApiIgnore @MemberClaim Member member, @RequestBody RegisterBoardRequest registerBoardRequest) {
        System.out.println("iubuvucgucguvyc");
        return ResponseEntity.ok(boardService.registerBoard(member, registerBoardRequest));
    }
}
