package com.fish.blog.controller;

import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.core.model.ResponseEntity;
import com.fish.core.utils.RedissionUtils;
import com.fish.product.model.Product;
import com.fish.product.service.ProductService;
import com.fish.shop.facade.CartFacade;
import com.fish.shop.model.Cart;
import com.fish.shop.model.CartProduct;
import com.fish.shop.service.CartProductService;
import com.fish.shop.service.CartService;
import com.fish.user.service.UserService;
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
    private CartFacade cartFacade;
    @Reference
    private UserService userService;

    @RequestMapping("getIndexProductList")
    @ResponseBody
    public ResponseEntity getIndexProductList(Product product){
        ResponseEntity result = ResponseEntity.SUCCESS;
        List<Product> productList = productService.getProductList(product);
        result.setResMsg(productList);
        return result;
    }

    @RequestMapping("getProductDetail")
    @ResponseBody
    public ResponseEntity getProductDetail(String id){
        ResponseEntity result = ResponseEntity.SUCCESS;
        Product product = productService.getProduct(Integer.valueOf(id));
        result.setResMsg(product);
        return result;
    }

    @RequestMapping("addToCart")
    @ResponseBody
    public ResponseEntity addToCart(String productId){
        ResponseEntity result = ResponseEntity.SUCCESS;

        /**
         * 1.加入缓存
         */
        //查询缓存中购物车的商品ids
        RedissonClient redissonClient = RedissionUtils.getInstance().getRedissionClient();

        //获取指定的节点值
        RList<String> rList = redissonClient.getList("cartProductIds");
        rList.add(productId);

        /**
         * 2.持久化
         */
        cartFacade.addToCart(productId);

        return result;
    }
}
