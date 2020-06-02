package com.fish.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fish.shop.dao.CartProductDao;
import com.fish.shop.model.CartProduct;
import com.fish.shop.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductDao cartProductDao;

    @Override
    public int insert(CartProduct cartProduct) {
        return cartProductDao.insert(cartProduct);
    }

    @Override
    public int update(CartProduct cartProduct){
        return cartProductDao.updateById(cartProduct);
    }

    @Override
    public CartProduct findById(Integer id){
        return cartProductDao.selectById(id);
    }

    @Override
    public CartProduct findByCondition(CartProduct cartProduct){
        return cartProductDao.selectOne(cartProduct);
    }

    @Override
    public List<CartProduct> findListByCondition(CartProduct cartProduct){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(cartProduct);
        return cartProductDao.selectList(entityWrapper);
    }
}
