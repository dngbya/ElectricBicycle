package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BaseContext;
import com.example.demo.entity.Breakdown;
import com.example.demo.entity.Charging;
import com.example.demo.service.BreakdownService;
import com.example.demo.service.ChargingService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/breakdown")
public class BreakdownController {
    @Autowired
    BreakdownService breakdownService;
    @Autowired
    ChargingService chargingService;
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,String pilePosition,String status){
        log.info("页数为：{}，大小为：{},pilePosition：{}",page,pageSize,pilePosition,status);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Breakdown> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(pilePosition),Breakdown::getPilePosition,pilePosition);
        queryWrapper.like(StringUtils.isNotEmpty(status),Breakdown::getStatus,status);
        queryWrapper.orderByAsc(Breakdown::getTime);
        breakdownService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody Breakdown breakdown){
        log.info("新增维修单，维修单：{}",breakdown.toString());
        Charging charging =chargingService.getById(breakdown.getPointId());
        String position=charging.getPosition();
        breakdown.setPilePosition(position);
        breakdown.setStatus("未完成");
        breakdown.setTime(LocalDateTime.now());
        breakdownService.save(breakdown);
        return R.success("新增维修单成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody Breakdown breakdown){
        breakdownService.updateById(breakdown);
        return R.success("维修单信息修改成功");
    }
    @GetMapping("/{id}")
    public R<Breakdown> getById(@PathVariable Long id){
        log.info("根据id查询维修单信息：{}",id);
        Breakdown breakdown=breakdownService.getById(id);
        if(breakdown!=null){
            return R.success(breakdown);
        }
        return R.error("没有查询到维修单信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的维修单id：{}",ids);
        breakdownService.removeByIds(ids);
        return R.success("删除成功");
    }
}
