package com.project.EComApplication.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;
    @PostMapping("/addProduct")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productMapper.toProductResponse(productService.createProduct(productRequest));
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return productMapper.toProductResponse(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public String updateProductById(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return productService.updateProductById(productRequest,id);
    }

    @DeleteMapping("/{id}")
    public String removeProductById(@PathVariable Long id){
        return productService.removeProductById(id);
    }
    @GetMapping("/search")
    public List<ProductResponse> findProductByKeyword(@RequestParam String keyword){
        return productService.findProductByKeyword(keyword);
    }
}