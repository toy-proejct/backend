package com.example.backend.api.board.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @ManyToOne
    private Board board;

    private String name;

    private BigDecimal cost;

    private String detail;

    private Integer quantity;

    private LocalDateTime purchaseTime;

    public Item(Board board, String name, BigDecimal cost, String detail, Integer quantity, LocalDateTime purchaseTime) {
        this.board = board;
        this.name = name;
        this.cost = cost;
        this.detail = detail;
        this.quantity = quantity;
        this.purchaseTime = purchaseTime;
    }
}
