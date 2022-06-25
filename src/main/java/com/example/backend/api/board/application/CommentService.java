package com.example.backend.api.board.application;

import com.example.backend.api.board.domain.Board;
import com.example.backend.api.board.domain.BoardRepository;
import com.example.backend.api.board.domain.Comment;
import com.example.backend.api.board.domain.CommentRepository;
import com.example.backend.api.board.dto.CommentResponse;
import com.example.backend.api.board.dto.RegisterCommentRequest;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentService(BoardRepository boardRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    public List<CommentResponse> findByBoard(Long boardId) {
        Board board = boardRepository.findByIdWithCheck(boardId);

        List<Comment> comments = commentRepository.findByBoard(board);

        List<Long> writerIds = getWriterIdsOfComment(comments);

        List<Member> writers = memberRepository.findAllById(writerIds);

        return comments.stream()
                .map(comment -> new CommentResponse(comment, findWriter(writers, comment)))
                .collect(Collectors.toList());
    }

    private List<Long> getWriterIdsOfComment(List<Comment> comments) {
        return comments.stream()
                .map(Comment::getWriterId)
                .collect(Collectors.toList());
    }

    private Member findWriter(List<Member> writers, Comment comment) {
        return writers.stream()
                .filter(writer -> writerIsEqual(comment, writer))
                .findFirst()
                .orElse(Member.DummyMember());
    }

    private boolean writerIsEqual(Comment comment, Member writer) {
        return writer.getId().equals(comment.getWriterId());
    }

    public void registerComment(Member member, RegisterCommentRequest registerCommentRequest) {
        Long boardId = registerCommentRequest.getBoardId();
        Board board = boardRepository.findByIdWithCheck(boardId);

        Comment comment = registerCommentRequest.toEntity(board, member.getId());

        commentRepository.save(comment);
    }
}
