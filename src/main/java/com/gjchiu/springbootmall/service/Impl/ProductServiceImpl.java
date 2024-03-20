package com.gjchiu.springbootmall.service.Impl;

import com.gjchiu.springbootmall.dao.ProductDao;
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
}
