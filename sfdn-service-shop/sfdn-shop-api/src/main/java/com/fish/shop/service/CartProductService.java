package com.fish.shop.service;

import com.fish.shop.model.CartProduct;

public interface CartProductService {
    int insert(CartProduct cartProduct);

    int update(CartProduct cartProduct);

    CartProduct selectByCondition(CartProduct cartProduct);
}
