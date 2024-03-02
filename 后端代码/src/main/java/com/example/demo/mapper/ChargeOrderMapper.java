package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ChargeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChargeOrderMapper extends BaseMapper<ChargeOrder> {
    @Select("select * from charge_order where user_id=#{id}")
    ChargeOrder getByUserId(Long id);
}
