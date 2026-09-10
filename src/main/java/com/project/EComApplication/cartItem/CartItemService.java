package com.project.EComApplication.cartItem;

import com.project.EComApplication.products.Product;
import com.project.EComApplication.products.ProductRepository;
import com.project.EComApplication.users.User;
import com.project.EComApplication.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public String addCartItems(String userId, CartItemRequest cartItem) {
        Optional<Product> productOptional = productRepository.findById(cartItem.getProductId());
        if (productOptional.isEmpty()) {
            return "invalid product id";
        }
        if (productOptional.get().getStockQuantity() < cartItem.getQuantity()) {
            return "product has only" + productOptional.get().getStockQuantity() + " stock/s left";
        }
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return "invalid User Id";
        }
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(userOptional.get(), productOptional.get());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(cartItem.getQuantity());
            existingCartItem.setPrice(productOptional.get().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        } else {
            cartItemRepository.save(new CartItem.CartItemBuilder()
                    .user(userOptional.get())
                    .product(productOptional.get())
                    .quantity(cartItem.getQuantity())
                    .price(productOptional.get().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                    .build());
        }
        return "success";
    }

    public String removeCartItems(String productId, String userId) {
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(productId));
        if (productOptional.isEmpty()) {
            return "invalid product id";
        }

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return "invalid User Id";
        }

        CartItem cartItem = cartItemRepository.findByUserAndProduct(userOptional.get()
                , productOptional.get());
        if (cartItem == null) {
            return "invalid item for this user";
        }
        cartItemRepository.delete(cartItem);
        return "removed successfully";
    }

    public Collection<CartItem> getAllCartItemByUser(String userId) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return new ArrayList<>();
        }
        System.out.println(userOptional.get());
        return cartItemRepository.findByUser(userOptional.get());
    }
}
