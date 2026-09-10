package com.project.EComApplication.cartItem;

import com.project.EComApplication.products.Product;
import com.project.EComApplication.users.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {
    private String name;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
