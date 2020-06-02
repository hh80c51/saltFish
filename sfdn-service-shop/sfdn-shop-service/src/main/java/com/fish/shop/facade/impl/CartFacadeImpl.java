package com.fish.shop.facade.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fish.product.model.Product;
import com.fish.product.service.ProductService;
import com.fish.shop.facade.CartFacade;
import com.fish.shop.model.Cart;
import com.fish.shop.model.CartProduct;
import com.fish.shop.service.CartProductService;
import com.fish.shop.service.CartService;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hh
 * @description
 * @date 2020/6/2  10:49
 */
@Service
public class CartFacadeImpl implements CartFacade {

    @Reference
    private ProductService productService;
    @Reference
    private CartService cartService;
    @Reference
    private CartProductService cartProductService;

    @Override
    @Compensable(confirmMethod = "confirmAddToCart", cancelMethod = "cancelAddToCart", transactionContextEditor = DubboTransactionContextEditor.class)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void addToCart(String productId){
        Product product = productService.getProduct(Integer.valueOf(productId));
        Cart cart = cartService.getCartByUserId(4);
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(Integer.valueOf(productId));
        cartProduct.setProductNum(1);
        cartProduct.setState(-1);
        cartProductService.insert(cartProduct);
        Cart c = null;
        c.setNum(111111);
        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.updateCart(cart);
    }

    @Override
    public void confirmAddToCart(String productId){
        Product product = productService.getProduct(Integer.valueOf(productId));
        Cart cart = cartService.getCartByUserId(4);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setState(1);
        cartProductService.update(cartProduct);

        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.updateCart(cart);
    }

    @Override
    public void cancelAddToCart(String productId){
        Product product = productService.getProduct(Integer.valueOf(productId));
        Cart cart = cartService.getCartByUserId(4);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setState(-1);
        cartProductService.update(cartProduct);

        cart.setNum(cart.getNum() - 1);
        cart.setPrice(cart.getPrice().subtract(product.getPrice()));
        cartService.updateCart(cart);
    }
}
