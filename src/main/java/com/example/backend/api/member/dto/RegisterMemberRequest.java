package com.example.backend.api.member.dto;

import com.example.backend.api.member.domain.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "회원가입 정보를 입력하는 객체")
public class RegisterMemberRequest {
    private MemberRequest memberRequest;

    private OauthRequest oauthRequest;

    public Member toMemberEntity(PasswordEncoder passwordEncoder) {
        return memberRequest.toMemberEntity(passwordEncoder);
    }

    public Member toMemberEntity(String email, PasswordEncoder passwordEncoder) {
        return memberRequest.toMemberEntity(email, passwordEncoder);
    }

    public Oauth toOauthEntity(Member member) {
        return oauthRequest.toOauthEntity(member);
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "회원의 정보를 입력하는 객체")
    public static class MemberRequest {
        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(value = "이메일")
        private String email;

        @NotEmpty(message = "닉네임은 필수 입력값입니다.")
        @ApiModelProperty(value = "닉네임")
        private String nickname;

        @NotEmpty(message = "전화번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식에 맞지 않습니다.")
        @ApiModelProperty(value = "전화번호")
        private String phone;

        @NotNull
        @ApiModelProperty(value = "비밀번호 : 소셜로그인인 경우 빈칸으로 입력")
        private String password;

        @NotNull
        @ApiModelProperty(value = "자기소개")
        private String introduce;

        private Member toMemberEntity(PasswordEncoder passwordEncoder) {
            return new Member(email, nickname, phone, passwordEncoder.encode(password), introduce, new WithdrawalInfo(), new BanInfo());
        }

        public Member toMemberEntity(String email, PasswordEncoder passwordEncoder) {
            return new Member(email, nickname, phone, passwordEncoder.encode(password), introduce, new WithdrawalInfo(), new BanInfo());
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "회원의 로그인 방식을 입력하는 객체")
    public static class OauthRequest {
        @NotNull
        @ApiModelProperty(value = "로그인 방식 선택")
        private ProviderType providerType;

        @NotNull
        @ApiModelProperty(value = "소셜로그인시 받는 id / 없을경우 빈칸")
        private String snsId;

        @NotNull
        @ApiModelProperty(value = "소셜로그인시 받는 accessToken / 없을경우 빈칸")
        private String accessToken;

        private Oauth toOauthEntity(Member member) {
            return new Oauth(member, snsId, accessToken, providerType);
        }
    }
}
