package com.viniccius.aws.spring_aws_microservices.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String model,
        String code,
        float price,
        String color
) {}
