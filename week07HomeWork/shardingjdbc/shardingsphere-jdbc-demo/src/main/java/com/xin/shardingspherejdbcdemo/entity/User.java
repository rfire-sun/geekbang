package com.xin.shardingspherejdbcdemo.entity;

import lombok.Data;

@Data
public class User {


    /**
     * 主键
     */
    private Integer id;
    private String name;
    private Integer age;

    public User() {

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


}
