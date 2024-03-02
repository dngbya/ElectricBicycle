package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.TopupOrder;
import com.example.demo.service.TopupOrderService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/topUpOrder")
public class TopupOrderController {
    @Autowired
    TopupOrderService topupOrderService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("页数为：{}，大小为：{}",page,pageSize);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<TopupOrder> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        //queryWrapper.eq(StringUtils.isNotEmpty(String.valueOf(pileId)),Breakdown::getPileId,pileId);
        queryWrapper.orderByDesc(TopupOrder::getTime);
        topupOrderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/getById")
    //RequestParam为数据设置默认值
    public R<Page> getById(int id){
        //构造分页构造器
        Page pageInfo=new Page(1,1000);
        //构造条件构造器
        LambdaQueryWrapper<TopupOrder> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(TopupOrder::getUserId,id);
        queryWrapper.orderByDesc(TopupOrder::getTime);
        topupOrderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody TopupOrder topupOrder){
        log.info("新增充值订单：{}",topupOrder.toString());
        topupOrder.setTime(LocalDateTime.now());
        topupOrderService.save(topupOrder);
        return R.success("新增充值订单成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody TopupOrder topupOrder){
        topupOrderService.updateById(topupOrder);
        return R.success("充值订单信息修改成功");
    }
    @GetMapping("/{id}")
    public R<TopupOrder> getById(@PathVariable Long id){
        log.info("根据id查询充值订单信息：{}",id);
        TopupOrder topupOrder=topupOrderService.getById(id);
        if(topupOrder!=null){
            return R.success(topupOrder);
        }
        return R.error("没有查询到充值订单信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的充值订单id：{}",ids);
        topupOrderService.removeByIds(ids);
        return R.success("删除成功");
    }
}
