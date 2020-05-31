package com.fish.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fish.core.commons.Constants;
import com.fish.core.model.ResponseEntity;
import com.fish.shop.dao.CartDao;
import com.fish.shop.model.Cart;
import com.fish.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/15
 * @Version V1.0
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    /**
     * 数据业务层getBean
     *
     * @return
     */
    @Override
    public Cart getCart(int id) {
        //业务层操作
        return cartDao.selectById(id);
    }

    @Override
    public Cart getCartByUserId(int id) {
        Cart cart = new Cart();
        cart.setUserId(id);
        return cartDao.selectOne(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartDao.updateAllColumnById(cart);
    }

    @Override
    public ResponseEntity isExist(Cart cartCdt) {
        ResponseEntity result = new ResponseEntity(Constants.SUCCESS);
        Cart cart = cartDao.selectOne(cartCdt);
        if(Objects.isNull(cart)){
            return new ResponseEntity(Constants.ERROR);
        }
        Map cartMap = new HashMap<>();
        cartMap.put("regCart", cart);
        result.setParams(cartMap);
        return result;
    }
}
