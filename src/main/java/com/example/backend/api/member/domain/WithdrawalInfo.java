package com.example.backend.api.member.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
public class WithdrawalInfo {
    @Column
    private boolean withdrawal;

    @Column
    private LocalDateTime withdrawalDatetime;

    public WithdrawalInfo(boolean withdrawal, LocalDateTime withdrawalDatetime) {
        this.withdrawal = withdrawal;
        this.withdrawalDatetime = withdrawalDatetime;
    }

    public boolean isWithdrawal() {
        return withdrawal;
    }

    public LocalDateTime getWithdrawalDatetime() {
        return withdrawalDatetime;
    }
}
