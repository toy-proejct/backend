package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Board;
import com.example.backend.api.board.domain.BoardTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardResponse {
    private Long categoryId;

    private String categoryName;

    private Long userId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long viewCount;

    private List<BoardTagResponse> boardTags = new ArrayList<>();

    public BoardResponse(Board board, List<BoardTag> boardTags) {
        categoryId = board.getCategory().getId();
        categoryName = board.getCategory().getName();
        userId = board.getUserId();
        title = board.getTitle();
        content = board.getContent();
        createdAt = board.getCreatedAt();
        modifiedAt = board.getModifiedAt();
        viewCount = board.getViewCount();
        this.boardTags = boardTags.stream()
                .map(BoardTagResponse::new)
                .collect(Collectors.toList());
    }
}
