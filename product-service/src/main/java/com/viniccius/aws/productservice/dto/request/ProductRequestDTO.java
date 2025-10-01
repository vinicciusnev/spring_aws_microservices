package com.viniccius.aws.productservice.dto.request;

public record ProductRequestDTO(
        Long id,
        String name,
        String model,
        String code,
        float price,
        String color
) {}
