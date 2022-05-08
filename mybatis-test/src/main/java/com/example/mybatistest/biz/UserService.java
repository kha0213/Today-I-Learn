package com.example.mybatistest.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public Users getUser(Long id) throws Exception {
        log.info("getUser id: [{}]", id);
        return userMapper.getUser(id);
    }

    public List<Users> getUserListAll() throws Exception {
        log.info("getUserListAll");
        return userMapper.getUserListAll();
    }

    public Users addUser(Users user) throws Exception {
        log.info("addUser : [{}]", user);
        userMapper.addUser(user);
        return userMapper.getUser(user.getId());
    }
}
