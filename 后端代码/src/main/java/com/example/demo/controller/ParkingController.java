package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Parking;
import com.example.demo.service.ParkingService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingService parkingService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,String position){
        log.info("页数为：{}，大小为：{},位置为:{}:",page,pageSize,position);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Parking> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(position), Parking::getPosition,position);
        queryWrapper.orderByAsc(Parking::getId);
        parkingService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody Parking parking){
        log.info("新增停车点：{}",parking.toString());
        parkingService.save(parking);
        return R.success("新增停车点成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody Parking parking){
        parkingService.updateById(parking);
        return R.success("停车点信息修改成功");
    }
    @GetMapping("/{id}")
    public R<Parking> getById(@PathVariable Long id){
        log.info("根据id查询停车点：{}",id);
        Parking parking=parkingService.getById(id);
        if(parking!=null){
            return R.success(parking);
        }
        return R.error("没有查询到停车点信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的停车点id：{}",ids);
        parkingService.removeByIds(ids);
        return R.success("删除成功");
    }
}
