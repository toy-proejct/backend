package com.example.backend.api.member.presentation;

import com.example.backend.api.member.application.MemberService;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.UpdateMemberRequest;
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

    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody RegisterMemberRequest registerMemberRequest) {
        memberService.registerMember(registerMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping
    public ResponseEntity<?> updateMember(@MemberClaim Member member, @RequestBody UpdateMemberRequest updateMemberRequest) {
        memberService.updateMember(member, updateMemberRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
