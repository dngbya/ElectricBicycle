package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.utils.PageBean;

public interface UserService extends IService<User> {
    void updateUser(Boolean status, String roleName);

    void updateMoney(int userId, int money);
}
