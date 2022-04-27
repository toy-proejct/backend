package com.example.backend.api.fixtures;

import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.RegisterMemberRequest;

public class MemberFixture {

    public static RegisterMemberRequest.RegisterMemberRequestBuilder aMember() {
        return RegisterMemberRequest.builder()
                .memberRequest(RegisterMemberRequest.MemberRequest.builder()
                        .email("test@test.com")
                        .nickname("test")
                        .phone("010-0000-0000")
                        .password("1q2w3e4r")
                        .introduce("test introduce")
                        .build())
                .oauthRequest(RegisterMemberRequest.OauthRequest.builder()
                        .snsId("")
                        .accessToken("")
                        .providerType(ProviderType.DEFAULT)
                        .build())
                ;
    }
}
