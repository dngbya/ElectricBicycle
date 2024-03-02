package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ChargingPile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChargingPileMapper extends BaseMapper<ChargingPile> {
    @Select("select point_id from charging_pile where id=#{id}")
    int getPointId(int pileId);
}
