package com.project.EComApplication.cartItem;

import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemResponse toCartItemResponse(CartItem cartItem){
        return CartItemResponse.builder()
                .name(cartItem.getUser().getName())
                .productName(cartItem.getProduct().getName())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
