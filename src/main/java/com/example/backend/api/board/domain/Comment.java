package com.example.backend.api.board.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @ManyToOne
    private Board board;

    private Long writerId;

    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Comment(Board board, Long writerId, String content) {
        this.board = board;
        this.writerId = writerId;
        this.content = content;
    }

    public Board getBoard() {
        return board;
    }

    public Long getWriterId() {
        return writerId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
