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
public class TopupOrder {
    private int id;
    private int money;
    private int userId;
    @TableField(fill= FieldFill.INSERT)//插入时填充字段
    private LocalDateTime time;
}
