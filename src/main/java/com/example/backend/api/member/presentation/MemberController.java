package com.example.backend.api.member.presentation;

import com.example.backend.api.member.application.MemberService;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.*;
import com.example.backend.common.exception.IllegalTokenException;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.security.annotations.MemberClaim;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "member login api", description = "<strong>기본 로그인일 경우 이메일과 비밀번호가 필요하며 소셜로그인일 경우 토큰이 필요함</strong>")
    @ApiResponses({
        @ApiResponse(responseCode = "400", description = "등록된 회원정보 없음"),
        @ApiResponse(responseCode = "401", description = "로그인 실패"),
        @ApiResponse(responseCode = "500", description = "서버 문제")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse token = memberService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "register member api", description = "<strong>소셜로 로그인을 하더라도 기본 정보 입력을 위한 회원가입 정보를 받아야 한다</strong>")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 문제")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@Valid @RequestBody RegisterMemberRequest registerMemberRequest) {
        memberService.registerMember(registerMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "select memberInfo api", description = "<strong>개인정보 조회를 위해 Bearer 혁식의 토큰이 필요함</strong>")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "등록된 회원정보 없음"),
            @ApiResponse(responseCode = "500", description = "토큰이 유효하지 않음")
    })
    @Authenticated
    @GetMapping
    public ResponseEntity<?> findMemberInfo(@ApiIgnore @MemberClaim Member member) {
        if (member.equals(Member.DummyMember())) {
            throw new NoSuchElementException("토큰정보가 유효하지 않습니다");
        }

        return ResponseEntity.ok(new MemberResponse(member));
    }

    @Operation(summary = "ㅕㅔㅇㅁㅅㄷ memberInfo api", description = "<strong>회원정보 수정을 위해 Bearer 혁식의 토큰이 필요함</strong>")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "등록된 회원정보 없음"),
            @ApiResponse(responseCode = "500", description = "토큰이 유효하지 않음")
    })
    @Authenticated
    @PutMapping
    public ResponseEntity<?> updateMember(@ApiIgnore @MemberClaim Member member, @Valid @RequestBody UpdateMemberRequest updateMemberRequest) {
        memberService.updateMember(member, updateMemberRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
