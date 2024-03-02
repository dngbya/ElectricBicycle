package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private int sex;
    private int age;
    private String avator;
    private String dept;
    private boolean status;
    private int money;
}
