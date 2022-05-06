package com.example.mybatistest.biz;

import com.example.mybatistest.TestConfig;
import com.example.mybatistest.config.DBConfig;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Rollback
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={DBConfig.class})
@TestPropertySource(locations = "classpath:application.properties")
@Import(TestConfig.class)
@MybatisTest
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("유저 등록 테스트")
    void addUser() throws Exception {
        Long id = 5L;
        Users user = new Users(id, "영롱이5");
        userMapper.addUser(user);

        Users findUser = userMapper.getUser(id);
        System.out.println("findUser = " + findUser);
        assertThat(findUser.getName()).isEqualTo("영롱이5");
    }


    @Test
    @DisplayName("유저 조회 테스트")
    void getUser() throws Exception {
        Long id = 2L;
        Users user = userMapper.getUser(id);
        assertThat(user.getName()).isEqualTo("영롱이");
    }

    @Test
    @DisplayName("유저 모두 조회 테스트")
    void getUserListAll() throws Exception {
        List<Users> user = userMapper.getUserListAll();

        user.forEach(System.out::println);
    }


}