package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeOrder {
    private int id;
    private int userId;
    private int pileId;
    private int pointId;
    private int carId;
    private int money;
    @TableField(fill= FieldFill.INSERT)//插入时填充字段
    private LocalDateTime startTime;
    @TableField(fill=FieldFill.INSERT)//插入时填充字段
    private LocalDateTime endTime;
}
