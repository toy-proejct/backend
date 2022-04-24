package com.example.backend.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@ApiModel(value = "로그인에 필요한 정보를 받는 객체")
public class LoginRequest {
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    private Provider provider;
}
