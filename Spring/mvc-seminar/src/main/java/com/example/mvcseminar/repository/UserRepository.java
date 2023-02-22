package com.example.mvcseminar.repository;

import com.example.mvcseminar.entity.User;
import com.example.mvcseminar.exception.UserException;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepository {

    public void save(User user) {
        if (Objects.requireNonNull(user).getName().contains("error")) {
            throw new UserException("비 정상 유저입니다.");
        }
    }
}
