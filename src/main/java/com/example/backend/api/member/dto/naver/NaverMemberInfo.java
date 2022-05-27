package com.example.backend.api.member.dto.naver;

import lombok.Data;

@Data
public class NaverMemberInfo {
    private String resultCode;
    private String message;
    private Response response;
}
