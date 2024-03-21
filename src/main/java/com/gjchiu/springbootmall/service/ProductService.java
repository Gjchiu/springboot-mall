package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.constant.ProductCategory;
import com.gjchiu.springbootmall.dto.ProductRequest;
import com.gjchiu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductCategory productCategory, String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProduct(Integer productId);

}
