package com.project.EComApplication.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    @PostMapping("/{userId}")
    public OrderResponse createOrder(@PathVariable String userId){
        return orderMapper.toOrderResponse(orderService.createOrder(userId));
    }
}
