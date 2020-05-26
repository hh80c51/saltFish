package com.fish.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fish.user.dao.UserDao;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/15
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    /**
     * 数据业务层getBean
     *
     * @return
     */
    @Override
    public User getUser(int id) {
        //业务层操作
        return userDao.selectById(id);
    }
}
