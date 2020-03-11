package com.fish.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("userInfo")
    public String findUserInfo(){
        System.out.println("after git方法：findUserInfo");
        return "blogDetail/blogSpring";
    }
}
