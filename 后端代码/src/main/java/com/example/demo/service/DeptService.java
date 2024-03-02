package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Dept;

public interface DeptService extends IService<Dept> {
    void updateUser(Boolean status,String dept);
}
