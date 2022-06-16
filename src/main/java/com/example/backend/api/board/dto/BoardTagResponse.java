package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.BoardTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardTagResponse {
    private Long tagId;
    private String tag;

    public BoardTagResponse(BoardTag boardTag) {
        tagId = boardTag.getId();
        tag = boardTag.getTag();
    }
}
