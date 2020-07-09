package com.fish.shop.facade.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fish.shop.facade.CartFacade;
import com.fish.shop.model.Cart;
import com.fish.shop.model.CartProduct;
import com.fish.shop.model.Product;
import com.fish.shop.service.CartProductService;
import com.fish.shop.service.CartService;
import com.fish.shop.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

/**
 * @author hh
 * @description
 * @date 2020/6/2  10:49
 */
@Service
public class CartFacadeImpl implements CartFacade {

    @Reference
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Reference
    private CartProductService cartProductService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=NullPointerException.class)
    @Override
//    @Compensable(confirmMethod = "confirmAddToCart", cancelMethod = "cancelAddToCart", transactionContextEditor = DubboTransactionContextEditor.class)
//    @GlobalTransactional(rollbackFor = Exception.class)
    public void addToCart(Integer productId, Integer userId){
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            Product product = productService.findById(productId);
            Cart cart = cartService.findByUserId(userId).get(0);
            //通过new Bean() 的构造方法的方式构造的,并没有托管个spring 所以 该类无法执行事务
//            CartProduct cartProduct = new CartProduct();
//            cartProduct.setCartId(cart.getId());
//            cartProduct.setProductId(Integer.valueOf(productId));
//            cartProduct.setProductNum(1);
//            cartProduct.setState(1);
//            cartProductService.insert(cartProduct);
//
//            cart.setNum(cart.getNum() + 1);
//            cart.setPrice(cart.getPrice().add(product.getPrice()));
//            cartService.update(cart);

            cart.setUserId(4);
            cart.setPrice(new BigDecimal(100));
            cart.setNum(2);
            cartService.insert(cart);

            Cart cartTest = null;
            cartTest.setNum(100);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
        }


    }

    @Override
    public void confirmAddToCart(Integer productId, Integer userId){
        Product product = productService.findById(productId);
        Cart cartCdt = new Cart();
        cartCdt.setUserId(userId);
        Cart cart = cartService.findByCondition(cartCdt);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setState(1);
        cartProductService.update(cartProduct);

        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.update(cart);
    }

    @Override
    public void cancelAddToCart(Integer productId, Integer userId){
        Product product = productService.findById(productId);
        Cart cartCdt = new Cart();
        cartCdt.setUserId(userId);
        Cart cart = cartService.findByCondition(cartCdt);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setState(-1);
        cartProductService.update(cartProduct);

        cart.setNum(cart.getNum() - 1);
        cart.setPrice(cart.getPrice().subtract(product.getPrice()));
        cartService.update(cart);
    }
}
