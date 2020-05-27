package com.fish.product.service;

import com.fish.core.model.ResponseEntity;
import com.fish.product.model.Product;

/**
 * @author hh
 */
public interface ProductService {
    /**
     * 数据业务层getBean
     * @return
     */
    Product getProduct(int id);

    ResponseEntity isExist(Product product);
}
