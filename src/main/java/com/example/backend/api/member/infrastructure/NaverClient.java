package com.example.backend.api.member.infrastructure;

import com.example.backend.api.member.dto.naver.NaverMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "naver", url = "https://openapi.naver.com")
public interface NaverClient {

    @GetMapping("/v1/nid/me")
    NaverMemberInfo getUserInfo(@RequestHeader("Authorization") String header);
}
