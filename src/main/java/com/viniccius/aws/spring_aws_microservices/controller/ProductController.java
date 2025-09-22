package com.viniccius.aws.spring_aws_microservices.controller;

import com.viniccius.aws.spring_aws_microservices.dto.request.ProductRequestDTO;
import com.viniccius.aws.spring_aws_microservices.dto.response.ProductResponseDTO;
import com.viniccius.aws.spring_aws_microservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/bycode")
    public ResponseEntity<ProductResponseDTO> findByCode(@RequestParam String code) {
        return ResponseEntity.ok(productService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO dto) {
        return new ResponseEntity<>(productService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
