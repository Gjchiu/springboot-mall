package com.gjchiu.springbootmall.service.Impl;

import com.gjchiu.springbootmall.dao.ProductDao;
import com.gjchiu.springbootmall.dto.ProductRequest;
import com.gjchiu.springbootmall.model.Product;
import com.gjchiu.springbootmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }
}
