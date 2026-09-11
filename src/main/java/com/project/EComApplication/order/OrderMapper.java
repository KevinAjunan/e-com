package com.project.EComApplication.order;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getOrderStatus())
                .items(order.getOrderItemList().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),orderItem.getProduct().getId(),orderItem.getQuantity()
                                ,orderItem.getPrice()
                                ,orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                        )).collect(Collectors.toList())
                )
                .createdAt(order.getCreatedAt())
                .build();
    }
}
