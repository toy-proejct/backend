package com.example.backend.api.craftshop.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessDay extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    private CraftShop craftShop;

    @Embedded
    private BusinessHour businessHour;

    public BusinessDay(DayOfWeek dayOfWeek, CraftShop craftShop, BusinessHour businessHour) {
        this.dayOfWeek = dayOfWeek;
        this.craftShop = craftShop;
        this.businessHour = businessHour;
    }
}
