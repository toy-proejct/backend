package com.example.backend.api.member.presentation;

import com.example.backend.api.member.application.MemberService;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.MemberResponse;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.security.annotations.MemberClaim;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<?> findMemberInfo(@MemberClaim Member member) {
        if (member.equals(Member.DummyMember())) {
            throw new RuntimeException("로그인 정보가 일치하지 않습니다");
        }
        return ResponseEntity.ok(new MemberResponse(member));
    }

    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody RegisterMemberRequest registerMemberRequest) {
        memberService.registerMember(registerMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Authenticated
    @PutMapping
    public ResponseEntity<?> updateMember(@MemberClaim Member member, @RequestBody UpdateMemberRequest updateMemberRequest) {
        memberService.updateMember(member, updateMemberRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
