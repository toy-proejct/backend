package com.example.backend.api.member.dto;

import com.example.backend.api.member.domain.Member;
import lombok.Data;

@Data
public class RegisterMemberRequest {
    private String email;
    private String nickname;
    private String phone;
    private String password;
    private String introduce;

    public Member toEntity() {
        return new Member(email, nickname, phone, password, introduce);
    }
}
