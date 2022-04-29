package com.example.backend.api.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class WithdrawalInfo {
    @Column
    private boolean withdrawal;

    @Column
    private LocalDateTime withdrawalDatetime;

    public boolean isWithdrawal() {
        return withdrawal;
    }

    public LocalDateTime getWithdrawalDatetime() {
        return withdrawalDatetime;
    }
}
