package com.example.backend.api.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class BanInfo {
    @Column
    private boolean banned;

    @Column
    private LocalDateTime bannedDateTime;

    @Column
    private LocalDateTime bannedDueDateTime;

    public void ban(long days) {
        banned = true;
        bannedDateTime = LocalDateTime.now();
        bannedDueDateTime = LocalDateTime.now()
                .plusDays(days);
    }

    public boolean isBanned() {
        if (banned) {
            validateBannedDate();
        }

        return banned;
    }

    private void validateBannedDate() {
        LocalDateTime now = LocalDateTime.now();
        if (bannedPeriodExpired(now)) {
            refresh();
        }
    }

    private boolean bannedPeriodExpired(LocalDateTime now) {
        return bannedDueDateTime == null || now.isAfter(bannedDueDateTime);
    }

    private void refresh() {
        banned = false;
        bannedDateTime = null;
        bannedDueDateTime = null;
    }
}
