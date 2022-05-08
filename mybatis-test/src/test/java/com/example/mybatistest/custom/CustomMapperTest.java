package com.example.mybatistest.custom;

import com.example.mybatistest.biz.Users;
import com.example.mybatistest.config.DBConfigTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Rollback
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={DBConfigTest.class})
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class CustomMapperTest {

    @Autowired
    CustomMapper mapper;

    @Test
    @DisplayName("커스텀 유저 테스트")
    void getUserByName() throws Exception {
        String name = "테스트3";
        Users userByName = mapper.getUserByName(name);

        log.info("findUser [{}]", userByName);
        assertThat(userByName.getName()).isEqualTo("테스트3");
    }


    @Nested
    @DisplayName("유저1 테스트")
    class userTest {
        @Test
        @DisplayName("TEST1")
        void test1() {

        }
        @Test
        @DisplayName("TEST2")
        void test2() {

        }
        @Test
        @DisplayName("TEST3")
        void test3() {

        }
    }


}