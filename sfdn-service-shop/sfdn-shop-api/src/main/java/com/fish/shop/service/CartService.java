package com.fish.shop.service;

import com.fish.shop.model.Cart;

import java.util.List;

/**
 * @author hh
 */
public interface CartService {
    int insert(Cart cart);
    int update(Cart cart);
    Cart findById(Integer id);
    Cart findByCondition(Cart cart);
    List<Cart> findByUserId(Integer userId);
    List<Cart> findListByCondition(Cart cart);
}
