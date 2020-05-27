package com.fish.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fish.core.commons.Constants;
import com.fish.core.model.ResponseEntity;
import com.fish.user.dao.UserDao;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
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

    @Override
    public ResponseEntity isExist(User userCdt) {
        ResponseEntity result = new ResponseEntity(Constants.SUCCESS);
        User user = userDao.selectOne(userCdt);
        if(Objects.isNull(user)){
            return new ResponseEntity(Constants.ERROR);
        }
        Map userMap = new HashMap<>();
        userMap.put("regUser", user);
        result.setParams(userMap);
        return result;
    }
}
