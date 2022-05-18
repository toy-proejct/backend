package com.example.backend.api.craftshop.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Column
    private String location;

    public Location(String location) {
        this.location = location;
    }
}
