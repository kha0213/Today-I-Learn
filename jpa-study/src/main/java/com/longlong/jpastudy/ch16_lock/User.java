package com.longlong.jpastudy.ch16_lock;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * 2021-07-28
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    private String name;
    @Version
    //@OptimisticLock(excluded = true)
    private int version;

    public User(String name) {
        this.name = name;
    }
}
