package com.example.backend.api.member.dto.kakao;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class KakaoMemberInfo {
    private String id;
    private ZonedDateTime connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;
}
