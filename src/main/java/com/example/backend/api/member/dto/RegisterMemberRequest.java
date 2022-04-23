package com.example.backend.api.member.dto;

import com.example.backend.api.member.domain.Member;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterMemberRequest {
    @Email
    private String email;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String phone;

    private String password;

    @NotEmpty
    private String introduce;

    public Member toEntity() {
        return new Member(email, nickname, phone, password, introduce);
    }
}
