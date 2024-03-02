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
public class Breakdown {
    private int id;
    private int pileId;
    private int userId;
    private int pointId;
    private String status;
    private String message;
    private String pilePosition;
    @TableField(fill= FieldFill.INSERT)//插入时填充字段
    private LocalDateTime time;
}
