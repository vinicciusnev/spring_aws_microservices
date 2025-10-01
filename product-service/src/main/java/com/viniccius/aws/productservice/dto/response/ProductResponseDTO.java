package com.viniccius.aws.productservice.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String model,
        String code,
        float price,
        String color
) {}
