package com.project.EComApplication.order;

import com.project.EComApplication.cartItem.CartItem;
import com.project.EComApplication.cartItem.CartItemRepository;
import com.project.EComApplication.cartItem.CartItemService;
import com.project.EComApplication.users.User;
import com.project.EComApplication.users.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemService cartItemService;
    @Transactional
    public Order createOrder(String userId) {
        List<CartItem> cartItemList = cartItemService.getAllCartItemByUser(userId);
        if(cartItemList.isEmpty()){
            return new Order();
        }
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty()){
            return new Order();
        }
        BigDecimal totalPrice = cartItemList.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

         Order order = new Order();
         order.setUser(userOptional.get());
         order.setOrderStatus(OrderStatus.CONFIRMED);
         order.setTotalAmount(totalPrice);
        List<OrderItem> orderItemList = cartItemList.stream()
                .map(cartItem -> new OrderItem(
                        null,cartItem.getProduct(),cartItem.getQuantity(),cartItem.getProduct().getPrice(),order
                )).collect(Collectors.toList());
        order.setOrderItemList(orderItemList);

        orderRepository.save(order);
        cartItemService.clearCart(userId);
        return order;
    }
}
