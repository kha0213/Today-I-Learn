package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Teacher;

public interface CustomRepository {
    Teacher findCustomById(Long id);
}
