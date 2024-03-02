package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Charging;
import com.example.demo.mapper.ChargingMapper;
import com.example.demo.service.ChargingService;
import org.springframework.stereotype.Service;

@Service
public class ChargingServiceImpl extends ServiceImpl<ChargingMapper, Charging> implements ChargingService {
}
