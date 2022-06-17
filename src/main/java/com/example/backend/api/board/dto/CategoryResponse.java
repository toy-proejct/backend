package com.example.backend.api.board.dto;

import com.example.backend.api.board.domain.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String name;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.name = category.getName();
    }
}
