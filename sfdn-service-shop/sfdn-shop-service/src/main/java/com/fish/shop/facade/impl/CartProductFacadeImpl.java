package com.fish.shop.facade.impl;

import com.fish.core.model.ResponseEntity;
import com.fish.shop.facade.CartProductFacade;
import com.fish.shop.model.Cart;
import com.fish.shop.model.CartProduct;
import com.fish.shop.model.Product;
import com.fish.shop.service.CartProductService;
import com.fish.shop.service.CartService;
import com.fish.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartProductFacadeImpl implements CartProductFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartProductService cartProductService;
    @Autowired


    public void addToCart(Integer productId, Integer userId){
        Product product = productService.findById(productId);
        Cart cartCdt = new Cart();
        cartCdt.setUserId(userId);
        Cart cart = cartService.findByCondition(cartCdt);
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(Integer.valueOf(productId));
        cartProduct.setProductNum(1);
        cartProductService.insert(cartProduct);
        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.update(cart);
    }
}
