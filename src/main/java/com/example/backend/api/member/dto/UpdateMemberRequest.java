package com.example.backend.api.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateMemberRequest {
    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @NotNull
    private String introduce;
}
