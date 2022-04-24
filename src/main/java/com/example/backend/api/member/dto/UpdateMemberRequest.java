package com.example.backend.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@ApiModel(value = "회원가입 정보를 입력하는 객체")
public class UpdateMemberRequest {
    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @NotNull
    @ApiModelProperty(value = "자기소개")
    private String introduce;
}
