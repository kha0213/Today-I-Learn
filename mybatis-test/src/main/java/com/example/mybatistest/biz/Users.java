package com.example.mybatistest.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Long id;
    private String name;

    public Users(String name) {
        this.name = name;
    }
}


