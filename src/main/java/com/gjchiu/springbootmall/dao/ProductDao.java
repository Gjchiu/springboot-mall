package com.gjchiu.springbootmall.dao;

import com.gjchiu.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
