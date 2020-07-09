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
import org.springframework.context.annotation.AdviceMode;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hh
 * @description
 * @date 2020/6/2  10:49
 */
@Service
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class CartFacadeImpl implements CartFacade {

    @Reference
    private ProductService productService;
    @Reference
    private CartService cartService;
    @Reference
    private CartProductService cartProductService;

    @Override
//    @Compensable(confirmMethod = "confirmAddToCart", cancelMethod = "cancelAddToCart", transactionContextEditor = DubboTransactionContextEditor.class)
//    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void addToCart(Integer productId, Integer userId){
        System.out.println(AnnotatedElementUtils.class);
        Product product = productService.findById(productId);
        Cart cart = cartService.findByUserId(userId).get(0);
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(Integer.valueOf(productId));
        cartProduct.setProductNum(1);
        cartProduct.setState(1);
        cartProductService.insert(cartProduct);

        Cart cartTest = null;
        cartTest.setNum(100);

        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.update(cart);
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
