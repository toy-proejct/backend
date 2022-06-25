package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Comment;
import com.example.backend.api.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentResponse {
    private String content;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    private Long writerId;

    private String writerName;

    public CommentResponse(Comment comment, Member writer) {
        content = comment.getContent();
        createAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
        writerId = writer.getId();
        writerName = writer.getNickname();
    }
}
