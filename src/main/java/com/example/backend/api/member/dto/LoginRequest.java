package com.example.backend.api.member.dto;

import com.example.backend.api.member.domain.ProviderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "로그인에 필요한 정보를 받는 객체")
public class LoginRequest {
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    private ProviderRequest providerRequest;

    public ProviderType getProviderType() {
        return providerRequest.getProviderType();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "로그인 방식을 입력하는 객체")
    public static class ProviderRequest {
        @ApiModelProperty(value = "로그인 방식")
        private ProviderType providerType;

        @ApiModelProperty(value = "소셜로그인 선택시 토큰값")
        private String token;
    }
}
