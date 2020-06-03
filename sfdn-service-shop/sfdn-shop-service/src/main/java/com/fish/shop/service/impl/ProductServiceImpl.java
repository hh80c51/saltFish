package com.fish.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fish.shop.dao.ProductDao;
import com.fish.shop.model.Product;
import com.fish.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public int insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public int update(Product product) {
        return productDao.updateById(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public Product findByCondition(Product product) {
        return productDao.selectOne(product);
    }

    @Override
    public List<Product> findListByCondition(Product product) {
        EntityWrapper entityWrapper = new EntityWrapper();
        //条件查询
//        wrapper.eq("state", "1");
        //设置select字段
//        wrapper.setSqlSelect("name");

        //打印sql语句
        logger.info(entityWrapper.getSqlSegment());

        entityWrapper.setEntity(product);
        return productDao.selectList(entityWrapper);
    }
}
