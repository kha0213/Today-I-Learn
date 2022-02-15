package com.study.querydsl.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchCond {
    private String memberName;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
