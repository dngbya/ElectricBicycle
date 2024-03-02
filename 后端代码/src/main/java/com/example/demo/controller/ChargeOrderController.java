package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BaseContext;
import com.example.demo.entity.ChargeOrder;
import com.example.demo.service.CarService;
import com.example.demo.service.ChargeOrderService;
import com.example.demo.service.UserService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chargingOrder")
public class ChargeOrderController {
    @Autowired
    ChargeOrderService chargeOrderService;
    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "100") Integer pageSize){
        log.info("页数为：{}，大小为：{}",page,pageSize);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<ChargeOrder> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        //queryWrapper.eq(StringUtils.isNotEmpty(String.valueOf(pileId)),Breakdown::getPileId,pileId);
        queryWrapper.orderByDesc(ChargeOrder::getEndTime);
        chargeOrderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/{id}")
    public R<Page> getById(int id){
        log.info("根据id查询充电订单信息：{}",id);
        //构造分页构造器
        Page pageInfo=new Page(1,1000);
        //构造条件构造器
        LambdaQueryWrapper<ChargeOrder> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(ChargeOrder::getUserId,id);
        queryWrapper.orderByDesc(ChargeOrder::getEndTime);
        chargeOrderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //获取用户姓名
    @GetMapping("/getPosition")
    public R<String> getPosition(int id){
        log.info("获取充电桩:{}的位置：",id);
        String position=chargeOrderService.getPosition(id);
        return R.success(position);
    }
    //获取充电桩位置
    @GetMapping("/getName")
    public R<String> getName(int id){
        log.info("获取用户:{}的名字：",id);
        String name=chargeOrderService.getName(id);
        return R.success(name);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody ChargeOrder chargeOrder){
        log.info("用户id为：{}",chargeOrder.getUserId());
        log.info("新增充电订单：{}",chargeOrder.toString());
        int carId=carService.getById(chargeOrder.getUserId()).getId();
        log.info("用户的车子为：{}",carId);
        chargeOrder.setCarId(carId);
        int money=userService.getById(chargeOrder.getUserId()).getMoney();
        if(money<2){
            return R.error("余额不足，请充值");
        }
        chargeOrder.setMoney(2);
        userService.updateMoney(chargeOrder.getUserId(),money-2);
        chargeOrder.setStartTime(LocalDateTime.now());
        chargeOrderService.save(chargeOrder);
        return R.success("新增充电订单成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody ChargeOrder chargeOrder) {
        chargeOrder.setEndTime(LocalDateTime.now());
        chargeOrderService.updateById(chargeOrder);
        return R.success("充电订单信息修改成功");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的充电订单id：{}",ids);
        chargeOrderService.removeByIds(ids);
        return R.success("删除成功");
    }
}
