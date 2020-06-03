package com.fish.shop.facade;

import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author hh
 * @description
 * @date 2020/6/2  10:48
 */
public interface CartFacade {
    @Compensable
    void addToCart(Integer productId, Integer userId);

    void confirmAddToCart(Integer productId, Integer userId);

    void cancelAddToCart(Integer productId, Integer userId);
}
