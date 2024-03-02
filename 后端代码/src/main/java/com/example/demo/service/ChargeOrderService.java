package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.ChargeOrder;
import com.example.demo.entity.User;

public interface ChargeOrderService extends IService<ChargeOrder> {
    String getName(int id);

    String getPosition(int id);

    ChargeOrder getByUserId(Long id);
}
