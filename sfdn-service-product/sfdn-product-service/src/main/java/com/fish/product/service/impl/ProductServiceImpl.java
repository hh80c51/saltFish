package com.fish.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fish.core.commons.Constants;
import com.fish.core.model.ResponseEntity;
import com.fish.product.dao.ProductDao;
import com.fish.product.model.Product;
import com.fish.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/15
 * @Version V1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;
    /**
     * 数据业务层getBean
     *
     * @return
     */
    @Override
    public Product getProduct(int id) {
        //业务层操作
        return productDao.selectById(id);
    }

    @Override
    public ResponseEntity isExist(Product productCdt) {
        ResponseEntity result = new ResponseEntity(Constants.SUCCESS);
        Product product = productDao.selectOne(productCdt);
        if(Objects.isNull(product)){
            return new ResponseEntity(Constants.ERROR);
        }
        Map productMap = new HashMap<>();
        productMap.put("regProduct", product);
        result.setParams(productMap);
        return result;
    }

    @Override
    public List<Product> getProductList(Product product) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        //条件查询
//        wrapper.eq("state", "1");
        wrapper.setEntity(product);
        //打印sql语句
        logger.info(wrapper.getSqlSegment());

        //设置select字段
//        wrapper.setSqlSelect("name");
        return productDao.selectList(wrapper);
    }
}
