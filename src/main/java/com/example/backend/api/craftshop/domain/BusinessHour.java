package com.example.backend.api.craftshop.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessHour {
    @Column
    private LocalTime businessHourFrom;

    @Column
    private LocalTime businessHourTo;

    public BusinessHour(LocalTime businessHourFrom, LocalTime businessHourTo) {
        this.businessHourFrom = businessHourFrom;
        this.businessHourTo = businessHourTo;
    }
}
