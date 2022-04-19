package com.example.backend.api.member.domain;

import com.example.backend.api.infra.BaseEntity;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    private String email;
    private String nickname;
    private String phone;
    private String password;

    public Member(String email, String nickname, String phone, String password) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
    }

    public void update(UpdateMemberRequest updateMemberRequest) {
        this.nickname = updateMemberRequest.getNickname();
    }

    public static Member DummyMember() {
        return new Member();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }
}
