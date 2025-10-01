package com.viniccius.aws.productservice.service;

import com.viniccius.aws.productservice.dto.request.ProductRequestDTO;
import com.viniccius.aws.productservice.dto.response.ProductResponseDTO;
import com.viniccius.aws.productservice.enums.EventType;
import com.viniccius.aws.productservice.exception.ProductNotFoundException;
import com.viniccius.aws.productservice.model.Product;
import com.viniccius.aws.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductPublisher productPublisher;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPublisher productPublisher) {
        this.productRepository = productRepository;
        this.productPublisher = productPublisher;
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto com id " + id + " não encontrado."));
        return toResponseDTO(product);
    }

    public ProductResponseDTO findByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Produto com código " + code + " não encontrado."));
        return toResponseDTO(product);
    }

    public ProductResponseDTO save(ProductRequestDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);

        productPublisher.publishProductEvent(product, EventType.PRODUCT_CREATED, "vinicciusnev");

        return toResponseDTO(saved);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto com id " + id + " não encontrado.");
        }

        Product product = toEntity(dto);
        product.setId(id);
        Product updated = productRepository.save(product);

        productPublisher.publishProductEvent(updated, EventType.PRODUCT_UPDATED, "vinicciusnev");

        return toResponseDTO(updated);
    }

    public ProductResponseDTO delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto com id " + id + " não encontrado."));

        productRepository.delete(product);

        productPublisher.publishProductEvent(product, EventType.PRODUCT_DELETED, "vinicciusnev");

        return toResponseDTO(product);
    }

    private Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.name())
                .code(dto.code())
                .price(dto.price())
                .model(dto.model())
                .color(dto.color())
                .build();
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getModel(),
                product.getCode(),
                product.getPrice(),
                product.getColor()
        );
    }
}