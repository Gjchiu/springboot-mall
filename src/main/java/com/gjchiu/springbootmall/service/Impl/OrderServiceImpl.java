package com.gjchiu.springbootmall.service.Impl;

import com.gjchiu.springbootmall.dao.OrderDao;
import com.gjchiu.springbootmall.dao.ProductDao;
import com.gjchiu.springbootmall.dto.BuyItem;
import com.gjchiu.springbootmall.dto.CreateOrderRequest;
import com.gjchiu.springbootmall.model.Order;
import com.gjchiu.springbootmall.model.OrderItem;
import com.gjchiu.springbootmall.model.Product;
import com.gjchiu.springbootmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private final ProductDao productDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount = totalAmount + amount;

            // 轉換ButItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return  order;
    }
}
