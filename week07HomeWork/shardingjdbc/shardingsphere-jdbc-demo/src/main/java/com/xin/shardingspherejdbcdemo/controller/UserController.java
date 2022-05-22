package com.xin.shardingspherejdbcdemo.controller;

import com.xin.shardingspherejdbcdemo.entity.User;
import com.xin.shardingspherejdbcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public Object saveUser() {
        return userService.save(new User("张三", 3));
    }

    @GetMapping("/listUser")
    public Object listUser() {
        return userService.list();
    }


}

