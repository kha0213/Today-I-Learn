package com.longlong.jpastudy.compositePK;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-02
 * Time: 오후 11:06
 */

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class UsersId implements Serializable {
    private Long id;
    private String name;
}
