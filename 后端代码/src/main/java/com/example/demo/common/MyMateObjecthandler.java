package com.example.demo.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
 * 自定义原数据处理器,自动填充数据
 * */
@Component
@Slf4j
public class MyMateObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //metaObject.setValue("time", LocalDateTime.now());
        //metaObject.setValue("startTime", LocalDateTime.now());
        //metaObject.setValue("createTime", LocalDateTime.now());
        /*metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());*/
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        long id=Thread.currentThread().getId();
        log.info("当前线程为：{}",id);
        /*metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());*/
    }
}
