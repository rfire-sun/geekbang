package com.xin.shardingspherejdbcdemo.mapper;

import com.xin.shardingspherejdbcdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insert(User record);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

}