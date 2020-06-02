package com.fish.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fish.shop.dao.CartDao;
import com.fish.shop.model.Cart;
import com.fish.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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

    @Override
    public int insert(Cart cart) {
        return cartDao.insert(cart);
    }

    @Override
    public int update(Cart cart) {
        return cartDao.updateById(cart);
    }

    @Override
    public Cart findById(Integer id) {
        return cartDao.selectById(id);
    }

    @Override
    public Cart findByCondition(Cart cart) {
        return cartDao.selectOne(cart);
    }

    @Override
    public List<Cart> findListByCondition(Cart cart) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(cart);
        return cartDao.selectList(entityWrapper);
    }
}
