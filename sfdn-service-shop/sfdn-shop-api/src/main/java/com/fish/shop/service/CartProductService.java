package com.fish.shop.service;

import com.fish.shop.model.CartProduct;

import java.util.List;

public interface CartProductService {
    int insert(CartProduct cartProduct);
    int update(CartProduct cartProduct);
    CartProduct findById(Integer id);
    CartProduct findByCondition(CartProduct cartProduct);
    List<CartProduct> findListByCondition(CartProduct cartProduct);
}
