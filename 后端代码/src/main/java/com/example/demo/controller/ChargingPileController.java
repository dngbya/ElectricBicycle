package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.ChargingPile;
import com.example.demo.entity.User;
import com.example.demo.service.ChargingPileService;
import com.example.demo.service.ChargingService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chargingPile")
public class ChargingPileController {
    @Autowired
    ChargingPileService chargingPileService;
    @Autowired
    ChargingService chargingService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,String position,String status){
        log.info("页数为：{}，大小为：{},充电点为：{}，状态为：{}：",page,pageSize,position,status);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<ChargingPile> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        //queryWrapper.eq(ChargingPile::getPointId,pointId);
        //queryWrapper.like(StringUtils.isNotEmpty(name), User::getName,name);
        queryWrapper.like(StringUtils.isNotEmpty(status),ChargingPile::getStatus,status);
        queryWrapper.like(StringUtils.isNotEmpty(position),ChargingPile::getPosition,position);
        queryWrapper.orderByAsc(ChargingPile::getPointId);
        chargingPileService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody ChargingPile chargingPile){
        log.info("新增充电桩：{}",chargingPile.toString());
        String position=chargingService.getById(chargingPile.getPointId()).getPosition();
        chargingPile.setPosition(position);
        chargingPileService.save(chargingPile);
        return R.success("新增充电桩成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody ChargingPile chargingPile){
        String position=chargingService.getById(chargingPile.getPointId()).getPosition();
        chargingPile.setPosition(position);
        chargingPileService.updateById(chargingPile);
        return R.success("充电桩信息修改成功");
    }
    @GetMapping("/{id}")
    public R<ChargingPile> getById(@PathVariable Long id){
        log.info("根据id查询充电桩：{}",id);
        ChargingPile chargingPile=chargingPileService.getById(id);
        if(chargingPile!=null){
            return R.success(chargingPile);
        }
        return R.error("没有查询到充电桩信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的充电桩id：{}",ids);
        chargingPileService.removeByIds(ids);
        return R.success("删除成功");
    }
}
