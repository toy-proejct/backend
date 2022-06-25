package com.example.backend.api.board.presentation;

import com.example.backend.api.board.application.CommentService;
import com.example.backend.api.board.dto.RegisterCommentRequest;
import com.example.backend.api.member.domain.Member;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.security.annotations.MemberClaim;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/boardId/{boardId}")
    public ResponseEntity<?> findByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.findByBoard(boardId));
    }

    @Authenticated
    @PostMapping("/register")
    public ResponseEntity<?> registerComment(@ApiIgnore @MemberClaim Member member, @RequestBody RegisterCommentRequest registerCommentRequest) {
        commentService.registerComment(member, registerCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
