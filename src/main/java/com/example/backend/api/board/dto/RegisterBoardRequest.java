package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Board;
import com.example.backend.api.board.domain.BoardTag;
import com.example.backend.api.board.domain.Category;
import com.example.backend.api.board.domain.Item;
import com.example.backend.api.member.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "게시글을 등록하는 객체")
public class RegisterBoardRequest {
    @ApiModelProperty(value = "카테고리 아이디")
    private Long categoryId;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String content;

    private List<RegisterBoardTagRequest> boardTags = new ArrayList<>();

    private List<RegisterItemRequest> items = new ArrayList<>();

    public Board toBoardEntity(Category category, Member member) {
        return new Board(category, member.getId(), title, content);
    }

    public List<BoardTag> toBoardTagEntity(Board board) {
        return boardTags.stream()
                .map(it -> it.toBoardTagEntity(board))
                .collect(Collectors.toList());
    }

    public List<Item> toItemEntity(Board board) {
        return items.stream()
                .map(it -> it.toItemEntity(board))
                .collect(Collectors.toList());
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterBoardTagRequest {
        @ApiModelProperty(value = "태그")
        private String tag;

        public BoardTag toBoardTagEntity(Board board) {
            return new BoardTag(board, tag);
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterItemRequest {
        @ApiModelProperty(value = "물품 이름")
        private String name;

        @ApiModelProperty(value = "가격")
        private BigDecimal cost;

        @ApiModelProperty(value = "설명")
        private String detail;

        @ApiModelProperty(value = "수량")
        private Integer quantity;

        @ApiModelProperty(value = "구매시기")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime purchaseTime;

        public Item toItemEntity(Board board) {
            return new Item(board, name, cost, detail, quantity, purchaseTime);
        }
    }
}
