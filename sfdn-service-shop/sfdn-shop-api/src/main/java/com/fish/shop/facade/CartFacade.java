package com.fish.shop.facade;

import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author hh
 * @description
 * @date 2020/6/2  10:48
 */
public interface CartFacade {
    @Compensable
    void addToCart(String productId);

    void confirmAddToCart(String productId);

    void cancelAddToCart(String productId);
}
