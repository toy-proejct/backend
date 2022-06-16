package com.example.backend.api.board.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
