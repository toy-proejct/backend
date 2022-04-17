package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
public class TestController {

    private final EntityManager entityManager;

    public TestController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/heartbeat")
    public String heartbeat() {
        entityManager.createQuery("select 1 from TestEntity t");
        return "OK";
    }
}
