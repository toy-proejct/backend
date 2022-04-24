package com.example.backend.common.security;

import com.example.backend.common.exception.IllegalTokenException;
import com.example.backend.common.utils.Pair;

public class BearerHeader {
    private static final String DELIMITER = " ";
    private static final String BEARER = "bearer ";

    public static Pair<String, String> splitToTokenFormat(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            throw new IllegalTokenException();
        }

        String[] split = authorization.split(DELIMITER);
        return new Pair<>(split[0], split[1]);
    }

    public static String of(String token) {
        return BEARER + token;
    }
}
