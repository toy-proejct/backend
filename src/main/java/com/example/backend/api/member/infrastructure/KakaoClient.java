package com.example.backend.api.member.infrastructure;

import com.example.backend.api.member.dto.kakao.KakaoMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "kakao", url = "https://kapi.kakao.com")
public interface KakaoClient {

    @GetMapping("/v2/user/me")
    KakaoMemberInfo getUserInfo(@RequestHeader("Authorization") String header);
}
