package com.fish.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fish.shop.dao.CartProductDao;
import com.fish.shop.model.CartProduct;
import com.fish.shop.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductDao cartProductDao;

    @Override
    public void insertCartProduct(CartProduct cartProduct) {
        cartProductDao.insert(cartProduct);
    }
}
