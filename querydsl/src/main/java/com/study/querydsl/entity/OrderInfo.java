package com.study.querydsl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity
public class OrderInfo {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

}
