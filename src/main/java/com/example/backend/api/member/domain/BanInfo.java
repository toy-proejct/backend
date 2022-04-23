package com.example.backend.api.member.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BanInfo {
    @Column
    private boolean banned;

    @Column
    private LocalDateTime bannedDateTime;

    @Column
    private LocalDateTime bannedDueDateTime;

    public BanInfo(boolean banned, LocalDateTime bannedDateTime, LocalDateTime bannedDueDateTime) {
        this.banned = banned;
        this.bannedDateTime = bannedDateTime;
        this.bannedDueDateTime = bannedDueDateTime;
    }

    public boolean getBanned() {
        return banned;
    }

    public LocalDateTime getBannedDateTime() {
        return bannedDateTime;
    }

    public LocalDateTime getBannedDueDateTime() {
        return bannedDueDateTime;
    }
}
