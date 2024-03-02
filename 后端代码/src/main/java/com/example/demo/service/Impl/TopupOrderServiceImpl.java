package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.TopupOrder;
import com.example.demo.mapper.TopupOrderMapper;
import com.example.demo.service.TopupOrderService;
import org.springframework.stereotype.Service;

@Service
public class TopupOrderServiceImpl extends ServiceImpl<TopupOrderMapper, TopupOrder> implements TopupOrderService {
}
