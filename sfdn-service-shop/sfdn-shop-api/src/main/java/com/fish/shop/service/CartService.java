package com.fish.shop.service;

import com.fish.core.model.ResponseEntity;
import com.fish.shop.model.Cart;

/**
 * @author hh
 */
public interface CartService {
    /**
     * 数据业务层getBean
     * @return
     */
    Cart getCart(int id);

    ResponseEntity isExist(Cart cart);
}
