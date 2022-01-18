package com.example.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class TeacherDto {
    private String name;

    private int age;

    private String subjectName;
}
