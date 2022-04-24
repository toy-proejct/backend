package com.example.backend.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ApiModel(value = "로그인 방식을 입력하는 객체")
public class Provider {
    @ApiModelProperty(value = "로그인 방식")
    private ProviderType providerType;

    @ApiModelProperty(value = "소셜로그인 선택시 토큰값")
    private String token;
}
