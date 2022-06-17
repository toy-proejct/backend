package com.example.backend.api.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Category findByIdWithCheck(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("카테고리가 없습니다"));
    }
}
