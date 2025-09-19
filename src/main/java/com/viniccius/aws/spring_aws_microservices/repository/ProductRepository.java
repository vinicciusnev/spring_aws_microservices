package com.viniccius.aws.spring_aws_microservices.repository;

import com.viniccius.aws.spring_aws_microservices.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
