package com.example.demo.controller;

import java.sql.SQLException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BaseContext;
import com.example.demo.entity.Dept;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request,@RequestBody User user) {
        log.info("登录请求，user为：{}",user);
        //1、将页面提交的密码password进行md5加密处理
        String password = user.getPassword();
        //password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户id查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, user.getId());
        User user1 = userService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (user1 == null) {
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!user1.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("user", user1.getId());
        BaseContext.setCurrentId((long) user1.getId());
        return R.success(user1);

       /* //登录成功，生成令牌，下发令牌

            Map<String, Object> cliams = new HashMap<>();
            cliams.put("id", usr1.getId());
            cliams.put("name", usr1.getName());
            String jwt = JwtUtils.generateJwt(cliams);
            log.info("令牌为：{}",jwt);
            return R.success(jwt);*/

    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        log.info("当前requestwe：{}",request);
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
    @GetMapping
    //RequestParam为数据设置默认值
    public R<Page> page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "100") Integer pageSize, String name,String dept){
        log.info("页数为：{}，大小为：{}，name为：{},dept为:{}",page,pageSize,name,dept);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getName,name);
        queryWrapper.like(StringUtils.isNotEmpty(dept),User::getDept,dept);
       /* queryWrapper.eq(User::getId,id);
        queryWrapper.eq(User::getSex,sex);*/
        queryWrapper.orderByAsc(User::getId);
        userService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    //新增
    @PostMapping
    public R<String> save(@RequestBody User user){
        log.info("新增用户，用户信息：{}",user.toString());
        user.setStatus(true);
        if(user.getDept()=="") {
            user.setDept("用户");
        }
        userService.save(user);
        return R.success("新增员工成功");
    }
    //修改
    @PutMapping
    public R<String> update(@RequestBody User user){
        //获取当前页面的id
        log.info("修改用户，用户信息：{}",user.toString());
            userService.updateById(user);
        return R.success("用户信息修改成功");
    }
    //修改状态
    @PutMapping("/updateStatus")
    public R<String> updateStatus(@RequestBody Dept dept){
        //获取当前页面的id
        log.info("修改职位，职位信息：{}",dept.toString());
        userService.updateUser(dept.getStatus(),dept.getRoleName());
        return R.success("职位信息修改成功");
    }
    public R<User> getById(@PathVariable Long id){
        log.info("根据id查询员工信息：{}",id);
        User employee=userService.getById(id);
        if(employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到员工信息");
    }
    //删除
    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable List<Integer> ids){
        log.info("删除的用户id：{}",ids);
        //categoryService.remove(id);
        userService.removeByIds(ids);
        return R.success("删除成功");
    }
}
