package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Breakdown;
import com.example.demo.entity.Charging;
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
@RequestMapping("/charging")
public class ChargingController {
    @Autowired
    ChargingService chargingService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,String position){
        log.info("页数为：{}，大小为：{}",page,pageSize);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Charging> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(position), Charging::getPosition,position);
        queryWrapper.orderByAsc(Charging::getId);
        chargingService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody Charging charging){
        log.info("新增充电点：{}",charging.toString());
        chargingService.save(charging);
        return R.success("新增充电点成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody Charging charging){
        chargingService.updateById(charging);
        return R.success("充电点信息修改成功");
    }
    @GetMapping("/{id}")
    public R<Charging> getById(@PathVariable Long id){
        log.info("根据id查询充电点信息：{}",id);
        Charging chargeOrder=chargingService.getById(id);
        if(chargeOrder!=null){
            return R.success(chargeOrder);
        }
        return R.error("没有查询到充电点信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的充电点id：{}",ids);
        chargingService.removeByIds(ids);
        return R.success("删除成功");
    }
}
