package com.example.springrest.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomHealthIndicator {

    @ReadOperation
    public String customEndpoint() {
        return "This is a custom endpoint";
    }

}