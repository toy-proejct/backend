package com.example.backend.api.member.domain;

import com.example.backend.api.infra.BaseEntity;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String introduce;

    @Column
    private int credibility;

    @CreatedDate
    private LocalDateTime createdAt;

    @Embedded
    private WithdrawalInfo withdrawalInfo;

    @Embedded
    private BanInfo banInfo;

    public Member(String email, String nickname, String phone, String password, String introduce) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.introduce = introduce;
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
