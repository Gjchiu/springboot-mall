package com.gjchiu.springbootmall.dao.impl;

import com.gjchiu.springbootmall.dao.ProductDao;
import com.gjchiu.springbootmall.model.Product;
import com.gjchiu.springbootmall.rowmapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        final String sql = "select product_id, product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date " +
                "from product where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map,new ProductRowMapper());
        if(!productList.isEmpty()){
            return productList.get(0);
        }else {
            return null;
        }
    }
}
