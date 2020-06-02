package com.fish.blog.controller;

import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.core.model.ResponseEntity;
import com.fish.core.utils.RedissionUtils;
import com.fish.shop.model.Cart;
import com.fish.shop.model.CartProduct;
import com.fish.shop.model.Product;
import com.fish.shop.service.CartProductService;
import com.fish.shop.service.CartService;
import com.fish.shop.service.ProductService;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import com.fish.user.util.BaseUtil;
import org.redisson.Config;
import org.redisson.RedissonClient;
import org.redisson.SingleServerConfig;
import org.redisson.client.RedisException;
import org.redisson.core.RList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductController
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Controller
@RequestMapping("productController")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Reference
    private ProductService productService;
    @Reference
    private CartProductService cartProductService;
    @Reference
    private CartService cartService;
    @Reference
    private UserService userService;

    @RequestMapping("getIndexProductList")
    @ResponseBody
    public ResponseEntity getIndexProductList(Product product){
        ResponseEntity result = ResponseEntity.SUCCESS;
        List<Product> productList = productService.findListByCondition(product);
        result.setResMsg(productList);
        return result;
    }

    @RequestMapping("getProductDetail")
    @ResponseBody
    public ResponseEntity getProductDetail(Integer id){
        ResponseEntity result = ResponseEntity.SUCCESS;
        Product product = productService.findById(id);
        result.setResMsg(product);
        return result;
    }

    @RequestMapping("addToCart")
    @ResponseBody
    public ResponseEntity addToCart(Integer productId){
        ResponseEntity result = ResponseEntity.SUCCESS;

        User loginUser = BaseUtil.getUser(() -> this.userService.findById(BaseUtil.getLoginUserId()));

        /**
         * 1.加入缓存
         */
        //查询缓存中购物车的商品ids
        RedissonClient redissonClient = RedissionUtils.getInstance().getRedissionClient();

        //获取指定的节点值
        RList<Integer> rList = redissonClient.getList("cartProductIds");
        rList.add(productId);

        /**
         * 2.持久化
         */
        Product product = productService.findById(productId);
        Cart cartCdt = new Cart();
        cartCdt.setUserId(loginUser.getId());
        Cart cart = cartService.findByCondition(cartCdt);
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(Integer.valueOf(productId));
        cartProduct.setProductNum(1);
        cartProductService.insert(cartProduct);
        cart.setNum(cart.getNum() + 1);
        cart.setPrice(cart.getPrice().add(product.getPrice()));
        cartService.update(cart);
        return result;
    }
}
