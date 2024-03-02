package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Dept;
import com.example.demo.entity.User;
import com.example.demo.service.DeptService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;
    @GetMapping
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize, String roleName) {
        log.info("页数为：{}，大小为：{}，roleName：{}", page, pageSize, roleName);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(roleName), Dept::getRoleName, roleName);
        queryWrapper.orderByAsc(Dept::getCreateTime);
        deptService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody Dept dept){
        //获取当前页面的id
        log.info("修改职位，职位信息：{}",dept.toString());
        deptService.updateById(dept);
        //deptService.updateUser(dept.getStatus(),dept.getRoleName());
        return R.success("职位信息修改成功");
    }
}
