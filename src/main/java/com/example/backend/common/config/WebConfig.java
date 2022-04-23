package com.example.backend.common.config;

import com.example.backend.common.security.JwtInterceptor;
import com.example.backend.common.security.JwtService;
import com.example.backend.common.security.JwtSessionArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtService jwtService;
    private final JwtSessionArgumentResolver jwtSessionArgumentResolver;

    public WebConfig(JwtService jwtService, JwtSessionArgumentResolver jwtSessionArgumentResolver) {
        this.jwtService = jwtService;
        this.jwtSessionArgumentResolver = jwtSessionArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtSessionArgumentResolver);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor(jwtService);
    }
}
