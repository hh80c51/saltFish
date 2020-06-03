package com.fish.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fish.core.commons.Constants;
import com.fish.core.model.ResponseEntity;
import com.fish.core.utils.HttpSessionUtil;
import com.fish.user.dao.UserDao;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return userDao.updateById(user);
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public User findByCondition(User user) {
        return userDao.selectOne(user);
    }

    @Override
    public List<User> findListByCondition(User user) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(user);
        return userDao.selectList(entityWrapper);
    }

    @Override
    public ResponseEntity isExist(User userCdt) {
        ResponseEntity result = new ResponseEntity(Constants.SUCCESS);
        User user = userDao.selectOne(userCdt);
        if(Objects.isNull(user)){
            return new ResponseEntity(Constants.ERROR, "用户名或密码不正确！");
        }
        Map userMap = new HashMap<>();
        userMap.put("loginUser", user);
        result.setParams(userMap);
        return result;
    }
}
