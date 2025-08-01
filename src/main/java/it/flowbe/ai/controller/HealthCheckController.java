package it.flowbe.ai.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/api/v1/isOk")
    public Map<String, String> isOk() {
        return Map.of("status", "OK");
    }
}