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
    public int insert(CartProduct cartProduct) {
        return cartProductDao.insert(cartProduct);
    }

    @Override
    public int update(CartProduct cartProduct) {
        return cartProductDao.updateAllColumnById(cartProduct);
    }

    @Override
    public CartProduct selectByCondition(CartProduct cartProduct) {
        return null;
    }
}
