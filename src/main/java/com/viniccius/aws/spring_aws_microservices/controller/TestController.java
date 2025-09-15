package com.viniccius.aws.spring_aws_microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        logger.info("Test controller - name: {}", name);
        return ResponseEntity.ok("Name: "  + name);
    }

    @PostMapping("/check-endpoint")
    public ResponseEntity<?> checkEndpoint() {
        logger.info("Test controller - checkEndpoint() OK");
        return ResponseEntity.ok("Test controller - checkEndpoint() OK");
    }
}
