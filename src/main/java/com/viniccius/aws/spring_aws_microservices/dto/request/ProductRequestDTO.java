package com.viniccius.aws.spring_aws_microservices.dto.request;

public record ProductRequestDTO(
        Long id,
        String name,
        String model,
        String code,
        float price,
        String color
) {}
