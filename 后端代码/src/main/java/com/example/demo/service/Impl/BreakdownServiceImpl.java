package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Breakdown;
import com.example.demo.mapper.BreakdownMapper;
import com.example.demo.service.BreakdownService;
import org.springframework.stereotype.Service;

@Service
public class BreakdownServiceImpl extends ServiceImpl<BreakdownMapper, Breakdown> implements BreakdownService {
}
