package com.project.EComApplication.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductValidation productValidation;

    public Product createProduct(ProductRequest productRequest) {

        return productRepository.save(productMapper.toProduct(productRequest));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("no Product with this id"));
    }

    public String updateProductById(ProductRequest productRequest, Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return "invalid id";
        }

        Product existingProduct = productOptional.get();

        if (productValidation.isEmpty(productRequest, productOptional.get())) {
            return "Data is empty";
        }
        productRepository.save(existingProduct);
        return "saved successfully";
    }

    public String removeProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return "invalid id";
        }
        productRepository.delete(productOptional.get());
        return "removed successfully";
    }

    public List<ProductResponse> findProductByKeyword(String keyword) {
        return productRepository.findByNameContaining(keyword).stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
