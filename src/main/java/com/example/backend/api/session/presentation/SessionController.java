package com.example.backend.api.session.presentation;

import com.example.backend.api.session.application.SessionService;
import com.example.backend.api.session.dto.LoginRequest;
import com.example.backend.api.session.dto.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse token = sessionService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(token);
    }
}
