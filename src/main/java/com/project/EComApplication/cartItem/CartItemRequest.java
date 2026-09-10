package com.project.EComApplication.cartItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
    private Long productId;
    private Integer quantity;
}
