package com.example.backend.api.member.dto;

import com.example.backend.api.member.domain.Member;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterMemberRequest {
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @NotEmpty(message = "전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식에 맞지 않습니다.")
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String introduce;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return new Member(email, nickname, phone, passwordEncoder.encode(password), introduce);
    }
}
