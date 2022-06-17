package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Board;
import com.example.backend.api.board.domain.BoardTag;
import com.example.backend.api.board.domain.Category;
import com.example.backend.api.member.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ApiModel(value = "게시글을 등록하는 객체")
public class RegisterBoardRequest {
    @ApiModelProperty(value = "카테고리 아이디")
    private Long categoryId;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String content;

    private List<RegisterBoardTagRequest> boardTags = new ArrayList<>();

    public Board toBoardEntity(Category category, Member member) {
        return new Board(category, member.getId(), title, content);
    }

    public List<BoardTag> toBoardTagEntity(Board board) {
        return boardTags.stream()
                .map(it -> new BoardTag(board, it.getTag()))
                .collect(Collectors.toList());
    }
}
