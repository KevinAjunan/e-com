package com.project.EComApplication.products;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductValidation {

    public boolean isEmpty(ProductRequest product, Product existingProduct) {
         boolean isEmpty =true;
        if(!product.getName().isEmpty()){
            existingProduct.setName(product.getName());
            isEmpty = false;

        }
        if(product.getCategory() != null && (!product.getCategory().isEmpty())){
            existingProduct.setCategory(product.getCategory());
            isEmpty = false;
        }
        if(product.getDescription() != null &&  !product.getDescription().isEmpty()){
            existingProduct.setDescription(product.getDescription());
            isEmpty = false;
        }
        if(product.getPrice() != null){
            existingProduct.setPrice(product.getPrice());
            isEmpty = false;
        }
        if(product.getImageUrl() != null && !product.getImageUrl().isEmpty()){
            existingProduct.setImageUrl(product.getImageUrl());
            isEmpty = false;
        }
        if(product.getStockQuantity() !=null){
            existingProduct.setStockQuantity(product.getStockQuantity());
            isEmpty = false;
        }
        return  isEmpty;
    }
}
