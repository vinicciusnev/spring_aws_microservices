package com.viniccius.aws.spring_aws_microservices.service;

import com.viniccius.aws.spring_aws_microservices.dto.request.ProductRequestDTO;
import com.viniccius.aws.spring_aws_microservices.dto.response.ProductResponseDTO;
import com.viniccius.aws.spring_aws_microservices.model.Product;
import com.viniccius.aws.spring_aws_microservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> findById(Long id) {
        return productRepository.findById(id).map(this::toResponseDTO);
    }

    public Optional<ProductResponseDTO> findByCode(String code) {
        return productRepository.findByCode(code).map(this::toResponseDTO);
    }

    public ProductResponseDTO save(ProductRequestDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    public Optional<ProductResponseDTO> update(Long id, ProductRequestDTO dto) {
        if (productRepository.existsById(id)) {
            Product product = toEntity(dto);
            product.setId(id);
            Product updated = productRepository.save(product);
            return Optional.of(toResponseDTO(updated));
        }

        return Optional.empty();
    }

    public Optional<ProductResponseDTO> delete(Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return toResponseDTO(product);
        });
    }

    private Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.name())
                .code(dto.code())
                .price(dto.price())
                .model(dto.model())
                .build();
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getModel(),
                product.getCode(),
                product.getPrice()
        );
    }
}