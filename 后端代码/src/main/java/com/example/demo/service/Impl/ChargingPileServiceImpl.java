package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ChargingPile;
import com.example.demo.mapper.ChargingPileMapper;
import com.example.demo.service.ChargingPileService;
import org.springframework.stereotype.Service;

@Service
public class ChargingPileServiceImpl extends ServiceImpl<ChargingPileMapper, ChargingPile> implements ChargingPileService {
}
