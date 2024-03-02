package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Car;
import com.example.demo.entity.User;
import com.example.demo.service.CarService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    //新增
    @PostMapping
    public R<String> save(@RequestBody Car car){
        log.info("新增车辆，用户信息：{}",car.toString());
        carService.save(car);
        return R.success("新增车辆成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody Car car){
        carService.updateById(car);
        return R.success("用户信息修改成功");
    }
    //根据用户id查询
    @GetMapping
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,String userName){
        log.info("根据userName查询车辆信息：{}",userName);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Car> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(userName),Car::getUserName,userName);
        carService.page(pageInfo,queryWrapper);
        if(pageInfo!=null){
            return R.success(pageInfo);
        }
        return R.error("没有查询到车辆信息");
    }
    //根据车牌号查询
    @GetMapping("/{id}")
    public R<Car> getById(@PathVariable Long id){
        log.info("根据id查询车辆信息：{}",id);
        Car car=carService.getById(id);
        if(car!=null){
            return R.success(car);
        }
        return R.error("没有查询到车辆信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的用户id：{}",ids);
        //categoryService.remove(id);
        carService.removeByIds(ids);
        return R.success("删除成功");
    }
}
