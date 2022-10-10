package com.example.jpashop.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 2022-10-10
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Data
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    private String street;
    private String city;
    private String zipcode;
}
