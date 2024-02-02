package com.archisacademy.ecommercespringboot.service.ipml;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUuid(UUID.randomUUID().toString());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        product.setCategory(productDto.getCategory());
        product.setUserLists(productDto.getUserLists());
        product.setPromotionList(productDto.getPromotionList());

        productRepository.save(product);

        return "Product created successfully";
    }

    @Override
    public String  updateProduct(ProductDto productDto) {
        Optional<Product> productForUpdate = Optional.ofNullable(productRepository.findByUuid(productDto.getUuid()));

        if (productForUpdate.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product product = productForUpdate.get();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        product.setCategory(productDto.getCategory());
        product.setUserLists(productDto.getUserLists());
        product.setPromotionList(productDto.getPromotionList());

        productRepository.save(product);

        return "Product updated successfully";

    }

    @Override
    public ProductDto getProductByUuid(String uuid) {
        Optional<Product> productForDb = Optional.ofNullable(productRepository.findByUuid(uuid));
        if (productForDb.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product product = productForDb.get();

        return new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory(),
                product.getUserLists(),
                product.getPromotionList()
        );
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory(),
                product.getUserLists(),
                product.getPromotionList()
        )).toList();
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> productForDeletion = productRepository.findById(id);
        if (productForDeletion.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryUuid) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByUser(String userUuid) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByPromotion(String promotionUuid) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByPrice(double price) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
        return null;
    }

}
