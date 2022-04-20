package com.xin.shardingspherejdbcdemo.service;



import com.xin.shardingspherejdbcdemo.entity.User;

import java.util.List;


public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User> list();

    /**
     * 单个 保存用户信息
     *
     * @param user
     */
    String save(User user);

}