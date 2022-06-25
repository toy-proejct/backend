package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Board;
import com.example.backend.api.board.domain.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "댓글을 등록하는 객체")
public class RegisterCommentRequest {

    private Long boardId;

    @ApiModelProperty(value = "내용")
    private String content;

    public Comment toEntity(Board board, Long writerId) {
        return new Comment(board, writerId, content);
    }
}
