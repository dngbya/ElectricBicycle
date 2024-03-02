package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Charging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChargingMapper extends BaseMapper<Charging> {
    @Select("select position from charging where id=#{id}")
    String getPosition(int id);
}
