package com.example.mybatistest.custom;

import com.example.mybatistest.biz.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomMapper {
    Users getUserByName(String name);
}
