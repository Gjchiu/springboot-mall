package com.gjchiu.springbootmall.dao;

import com.gjchiu.springbootmall.constant.ProductCategory;
import com.gjchiu.springbootmall.dto.ProductQueryParams;
import com.gjchiu.springbootmall.dto.ProductRequest;
import com.gjchiu.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProduct(Integer productId);

}
