package com.longlong.jpastudy.compositePK;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-02
 * Time: 오후 11:04
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(UsersId.class)
@ToString
public class Users implements Serializable {
    // 식별자 클래스를 별도로 만들 것이다.
//    @Id
//    @Column(name = "ID")
//    private Long id;
//    @Id
//    @Column(name = "NAME")
//    private String name;

    @EmbeddedId
    private UsersId usersId;

    private int age;
}
