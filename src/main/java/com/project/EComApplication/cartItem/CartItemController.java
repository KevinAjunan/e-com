package com.project.EComApplication.cartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartItemMapper cartItemMapper;
    @GetMapping
    public List<CartItemResponse> getAllCartItems(){
        return cartItemService.getAllCartItems().stream()
                .map(cartItemMapper::toCartItemResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public String addCartItems(@RequestParam String userId, @RequestBody CartItemRequest cartItemRequest){
        return cartItemService.addCartItems(userId,cartItemRequest);
    }

    @DeleteMapping("/remove/{userId}")
    public String removeCartItems(@RequestParam String productId, @PathVariable String userId){
        return cartItemService.removeCartItems(productId,userId);
    }

    @GetMapping("/{userId}")
    public List<CartItemResponse> getAllCartItemByUser(@PathVariable String userId){
        return cartItemService.getAllCartItemByUser(userId).stream()
                .map(cartItemMapper::toCartItemResponse)
                .collect(Collectors.toList());
    }
}
