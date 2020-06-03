package com.fish.shop.service;

import com.fish.shop.model.Product;

import java.util.List;

/**
 * @author hh
 */
public interface ProductService {
    int insert(Product product);
    int update(Product product);
    Product findById(Integer id);
    Product findByCondition(Product product);
    List<Product> findListByCondition(Product product);
}
