package com.fish.blog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.user.model.User;
import com.fish.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("userInfo")
    public String findUserInfo(){
        System.out.println("方法：findUserInfo");
//        User user = userService.getUser(1);
//        System.out.println(user.getName());
        return "/test";
    }
}
