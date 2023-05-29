package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserLoginMapper {
    //根据openid查询用户
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    //插入数据
    void insert(User user);

    @Select("select * from user where id = #{userId}")
    User getById(Long userId);

    //用户统计
    Integer countByMap(Map map);
}
