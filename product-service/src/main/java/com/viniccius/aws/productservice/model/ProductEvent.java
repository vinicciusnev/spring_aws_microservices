package com.viniccius.aws.productservice.model;

import lombok.Data;

@Data
public class ProductEvent {
    private Long productId;
    private String code;
    private String username;
}
