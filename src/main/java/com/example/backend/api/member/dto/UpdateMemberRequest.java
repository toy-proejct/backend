package com.example.backend.api.member.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateMemberRequest {
    @NotEmpty
    private String nickname;
}
