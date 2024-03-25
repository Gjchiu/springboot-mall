package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.dto.CreateOrderRequest;
import com.gjchiu.springbootmall.dto.OrderQueryParams;
import com.gjchiu.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
}
