package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@RestController
public class TestController {

    private final EntityManager entityManager;

    public TestController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/heartbeat")
    public BigInteger heartbeat() {
        return (BigInteger) entityManager.createNativeQuery("select count(*) from test_entity").getSingleResult();
    }
}
