package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Charging {
    private int id;
    private String position;
    private String image;
    private int number;
    private int usingNumber;
    private int freeNumber;
    private int attendantId;
}
