package com.fish.blog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.user.model.ResponseEntity;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.provider.certpath.ResponderId;

@Controller
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;

    @RequestMapping("userInfo")
    public String findUserInfo(){
        logger.info("方法：findUserInfo");
        User user = userService.getUser(1);
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
    public String login(User user){
        ResponseEntity res = userService.isExist(user);
        return "/shop/index";
    }
}
