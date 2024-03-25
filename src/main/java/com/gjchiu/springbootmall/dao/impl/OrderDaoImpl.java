package com.gjchiu.springbootmall.dao.impl;

import com.gjchiu.springbootmall.dao.OrderDao;
import com.gjchiu.springbootmall.dto.CreateOrderRequest;
import com.gjchiu.springbootmall.model.Order;
import com.gjchiu.springbootmall.model.OrderItem;
import com.gjchiu.springbootmall.rowmapper.OrderItemRowMapper;
import com.gjchiu.springbootmall.rowmapper.OrderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        final String sql = "insert into `order` (user_id, total_amount, created_date, last_modified_date) " +
                "values (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date today = new Date();
        map.put("createdDate", today);
        map.put("lastModifiedDate", today);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map) , keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        // 使用batchUpdate一次性加入數據，效率較高
        final String sql = "insert into order_item (order_id, product_id, quantity, amount)" +
                " values (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for(int i = 0;i < orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        final String sql = "select order_id, user_id, total_amount, created_date, last_modified_date from `order` " +
                "where order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if(orderList.size() > 0){
            return orderList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        final String sql = "select o.order_item_id, o.order_id, o.product_id, o.quantity, o.amount, p.product_name,  p.image_url" +
                " from order_item o" +
                " left join product p on o.product_id = p.product_id" +
                " where o.order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;
    }
}
