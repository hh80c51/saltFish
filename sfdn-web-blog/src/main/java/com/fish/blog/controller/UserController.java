package com.fish.blog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.core.model.ResponseEntity;
import com.fish.core.utils.HttpSessionUtil;
import com.fish.core.utils.RedissionUtils;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.redisson.core.RBucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;
    
    /**
     * @description 限流测试方法
     * @param
     * @return java.lang.String
     * @date 2020/6/2 22:31
     * @author hh
     */
    @RequestMapping("userInfo")
    public String findUserInfo(){
        logger.info("方法：findUserInfo");
        User user = userService.findById(1);
        logger.info(user.getName());
        return "/user";
    }

    /**
     * @MethodName: login
     * @Description: 登录
     * @Param: [user]
     * @Return: java.lang.String
     * @Author: hh
     * @Date: 2020/5/26
    **/
    @RequestMapping("login")
    @ResponseBody
    public ResponseEntity login(User user){
        ResponseEntity res =  userService.isExist(user);
        if(res.validSuc()){
            user = (User) res.getParams().get("loginUser");
            // 保存到session中
            HttpSessionUtil.addLoginUser(user);
            // 保存到redis中
            RBucket<User> bucket = RedissionUtils.getInstance().getRedissionClient().getBucket(
                    user.getClass().getSimpleName() + user.getId());
            bucket.set(user);
        }
        return res;
    }
}
