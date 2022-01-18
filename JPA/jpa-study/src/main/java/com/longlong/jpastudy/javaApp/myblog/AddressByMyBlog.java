package com.longlong.jpastudy.javaApp.myblog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-22.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressByMyBlog {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    private String street;

    private String zip;

    private String city;

    @OneToOne(mappedBy = "address")
    private Student student;

    public AddressByMyBlog(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }
}
