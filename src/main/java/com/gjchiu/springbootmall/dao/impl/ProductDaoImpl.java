package com.gjchiu.springbootmall.dao.impl;

import com.gjchiu.springbootmall.dao.ProductDao;
import com.gjchiu.springbootmall.dto.ProductQueryParams;
import com.gjchiu.springbootmall.dto.ProductRequest;
import com.gjchiu.springbootmall.model.Product;
import com.gjchiu.springbootmall.rowmapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        final String sql = "insert into product " +
                "(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "values (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date today = new Date();
        map.put("createdDate", today);
        map.put("lastModifiedDate", today);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map) , keyHolder);
        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        final String sql = "update product set " +
                "product_name = :productName, category = :category, image_url = :imageUrl, price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date today = new Date();
        map.put("lastModifiedDate", today);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        final String sql = "delete from product where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "select product_id, product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date from product " +
                "where 1=1";

        Map<String,Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        //排序
        sql = sql + " order by " + productQueryParams.getOrder() + " " + productQueryParams.getSort();

        //分頁
        sql = sql + " limit :limit offset :offset ";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map,new ProductRowMapper());

        return productList;
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "select count(1) from product where 1=1";

        Map<String,Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    private String addFilteringSql(String sql, Map map, ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory() != null){
            sql = sql + " and category = :category";
            map.put("category",productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch() != null){
            sql = sql + " and product_name like :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        return sql;
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        final String sql = "update product set stock = :stock, last_modified_date = :lastModifiedDate " +
                " where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("stock", stock);

        Date today = new Date();
        map.put("lastModifiedDate", today);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
