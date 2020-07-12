package com.fish.shop.demo;

import com.fish.shop.facade.CartFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Auther: Administrator
 * @Date: 2020/7/12 16:59
 */
public class CartFacadeTest {
    @Autowired
    private CartFacade cartFacade;

    /**
     * @description 测试本地持久化操作，及发生异常事务回滚的场景
     * @param
     * @return void
     * @date 2020/7/12 17:16
     * @author hh
     */
    @Test
    public void Test(){
        cartFacade.addToCart(1, 4);
    }
}
