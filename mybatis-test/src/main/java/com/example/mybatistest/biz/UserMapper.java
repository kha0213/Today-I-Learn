package com.example.mybatistest.biz;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Users getUser(Long id) throws Exception;

    void addUser(Users user);

    List<Users> getUserListAll();
}
