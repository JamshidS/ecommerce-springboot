package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProductByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(productService.getProductByUuid(uuid));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryUuid}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String categoryUuid) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryUuid));
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable String userUuid) {
        return ResponseEntity.ok(productService.getProductsByUser(userUuid));
    }

    @GetMapping("/promotion/{promotionUuid}")
    public ResponseEntity<List<ProductDto>> getProductsByPromotion(@PathVariable String promotionUuid) {
        return ResponseEntity.ok(productService.getProductsByPromotion(promotionUuid));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDto>> getProductsByPrice(@PathVariable double price) {
        return ResponseEntity.ok(productService.getProductsByPrice(price));
    }

    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }
}
