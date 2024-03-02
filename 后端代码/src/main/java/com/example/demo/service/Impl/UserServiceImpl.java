package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void updateUser(Boolean status, String roleName) {
        userMapper.updateUser(status,roleName);
    }

    @Override
    public void updateMoney(int userId, int money) {
        userMapper.updateMoney(userId,money);
    }
}
