package com.example.backend.api.craftshop.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftShop extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    private String introduce;

    private Long operatorId;

    @Enumerated(EnumType.STRING)
    private CraftShopStatus status = CraftShopStatus.PENDING;

    @Embedded
    private BusinessHour businessHour;

    @Embedded
    private Location location;

    public CraftShop(String name, String phone, String introduce, Long operatorId, BusinessHour businessHour, Location location) {
        this.name = name;
        this.phone = phone;
        this.introduce = introduce;
        this.operatorId = operatorId;
        this.businessHour = businessHour;
        this.location = location;
    }

    public void activate() {
        status = CraftShopStatus.ACTIVE;
    }

    public void inactivate() {
        status = CraftShopStatus.INACTIVE;
    }
}
