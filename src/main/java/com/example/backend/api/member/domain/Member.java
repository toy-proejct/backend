package com.example.backend.api.member.domain;

import com.example.backend.api.infra.BaseEntity;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import com.example.backend.common.exception.AuthorizationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column
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

    public void checkPassword(String password, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new AuthorizationException(AuthorizationException.Code.WRONG_MEMBER_INFO);
        }
    }

    public void update(UpdateMemberRequest updateMemberRequest) {
        this.nickname = updateMemberRequest.getNickname();
        this.introduce = updateMemberRequest.getIntroduce();
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

    public String getPassword() {
        return password;
    }

    public String getIntroduce() {
        return introduce;
    }

    public int getCredibility() {
        return credibility;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public WithdrawalInfo getWithdrawalInfo() {
        return withdrawalInfo;
    }

    public BanInfo getBanInfo() {
        return banInfo;
    }
}
