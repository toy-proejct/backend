package com.example.backend.api.board.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardTag extends BaseEntity {

    @ManyToOne
    private Board board;

    private String tag;

    public BoardTag(Board board, String tag) {
        this.board = board;
        this.tag = tag;
    }

    public Board getBoard() {
        return board;
    }

    public String getTag() {
        return tag;
    }
}
