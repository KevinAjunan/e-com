package com.project.EComApplication.products;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .active(product.getIsActive())
                .build();
    }

    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .imageUrl(productRequest.getImageUrl())
                .isActive(true)
                .build();
    }
}
