package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.dto.CreateOrderRequest;
import com.gjchiu.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
