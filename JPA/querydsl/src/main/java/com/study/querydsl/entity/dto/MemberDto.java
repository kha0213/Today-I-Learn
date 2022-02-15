package com.study.querydsl.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String username;

    private int age;

    private String teamName;

    @QueryProjection
    public MemberDto(String username, int age, String teamName) {
        this.username = username;
        this.age = age;
        this.teamName = teamName;
    }
}
