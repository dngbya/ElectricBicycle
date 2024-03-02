package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ChargeOrder;
import com.example.demo.entity.ChargingPile;
import com.example.demo.mapper.ChargeOrderMapper;
import com.example.demo.mapper.ChargingMapper;
import com.example.demo.mapper.ChargingPileMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ChargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeOrderServiceImpl extends ServiceImpl<ChargeOrderMapper, ChargeOrder> implements ChargeOrderService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChargingMapper chargingMapper;
    @Autowired
    ChargingPileMapper chargingPileMapper;
    @Autowired
    ChargeOrderMapper chargeOrderMapper;
    @Override
    public String getName(int id) {
        return userMapper.getName(id);
    }

    @Override
    public String getPosition(int pileId) {
        int id=chargingPileMapper.getPointId(pileId);
        return chargingMapper.getPosition(id);
    }

    @Override
    public ChargeOrder getByUserId(Long id) {
        return chargeOrderMapper.getByUserId(id);
    }
}
