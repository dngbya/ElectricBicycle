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
public class Dept {
    private String roleName;
    private String id;
    private String roleIdentification;
    private String describeing;
    @TableField(fill= FieldFill.INSERT)//插入时填充字段
    private LocalDateTime createTime;
    private Boolean status;
}
