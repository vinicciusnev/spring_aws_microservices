package com.viniccius.aws.spring_aws_microservices.model;

import lombok.Data;

@Data
public class ProductEvent {
    private Long productId;
    private String code;
    private String username;
}
