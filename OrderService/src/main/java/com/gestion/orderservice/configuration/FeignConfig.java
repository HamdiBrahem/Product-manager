package com.gestion.orderservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Replace "your_jwt_token" with a valid token or retrieve it dynamically
                requestTemplate.header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NSwic3ViIjoibmlvbiIsImlhdCI6MTczNjEwOTIxMywiZXhwIjoxNzM2MTk1NjEzfQ.OH4aHB1q124f-vlzAwsN3z5wEynFvRVs6eoO7sxd_O8");
            }
        };
    }
}
