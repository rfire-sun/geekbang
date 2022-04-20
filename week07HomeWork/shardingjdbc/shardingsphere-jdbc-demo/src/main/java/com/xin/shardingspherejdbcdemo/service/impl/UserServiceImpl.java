package com.xin.shardingspherejdbcdemo.service.impl;


import com.xin.shardingspherejdbcdemo.entity.User;
import com.xin.shardingspherejdbcdemo.mapper.UserMapper;
import com.xin.shardingspherejdbcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String save(User user) {

        userMapper.insert(user);
        return "保存成功";
    }
}
