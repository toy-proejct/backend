package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class TestController {

    private final EntityManager entityManager;

    public TestController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/heartbeat")
    public List<TestEntity> heartbeat() {
        return entityManager.createQuery("select 1 from TestEntity t", TestEntity.class).getResultList();
    }
}
