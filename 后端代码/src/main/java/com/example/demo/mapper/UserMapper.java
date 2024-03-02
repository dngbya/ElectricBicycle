package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void updateUser(Boolean status, String dept);

    @Select("select name from user where id=#{id}")
    String getName(int id);

    @Update("update user set money=#{money} where id=#{userId}")
    void updateMoney(int userId, int money);
}