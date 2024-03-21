package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.dto.ProductRequest;
import com.gjchiu.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProduct(Integer productId);
}
