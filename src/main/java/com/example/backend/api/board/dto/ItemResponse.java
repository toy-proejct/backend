package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemResponse {

    private String name;

    private BigDecimal cost;

    private String detail;

    private Integer quantity;

    private LocalDateTime purchaseTime;

    public ItemResponse(Item item) {
        this.name = item.getName();
        this.cost = item.getCost();
        this.detail = item.getDetail();
        this.quantity = item.getQuantity();
        this.purchaseTime = item.getPurchaseTime();
    }
}
