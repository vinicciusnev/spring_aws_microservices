package com.viniccius.aws.spring_aws_microservices.service;

import com.viniccius.aws.spring_aws_microservices.dto.request.ProductRequestDTO;
import com.viniccius.aws.spring_aws_microservices.dto.response.ProductResponseDTO;
import com.viniccius.aws.spring_aws_microservices.exception.ProductNotFoundException;
import com.viniccius.aws.spring_aws_microservices.model.Product;
import com.viniccius.aws.spring_aws_microservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        return toResponseDTO(saved);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto com id " + id + " não encontrado.");
        }

        Product product = toEntity(dto);
        product.setId(id);
        Product updated = productRepository.save(product);
        return toResponseDTO(updated);
    }

    public ProductResponseDTO delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto com id " + id + " não encontrado."));
        productRepository.delete(product);
        return toResponseDTO(product);
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