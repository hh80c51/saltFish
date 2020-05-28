package com.fish.blog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.core.model.ResponseEntity;
import com.fish.product.model.Product;
import com.fish.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Reference
    private ProductService productService;

    @RequestMapping("getIndexProductList")
    @ResponseBody
    public ResponseEntity getIndexProductList(Product product){
        ResponseEntity result = ResponseEntity.SUCCESS;
        List<Product> productList = productService.getProductList(product);
        result.setResMsg(productList);
        return result;
    }
}
