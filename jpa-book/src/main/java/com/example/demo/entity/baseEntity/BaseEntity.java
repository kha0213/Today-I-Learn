package com.example.demo.entity.baseEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 2021-02-22
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    private String createdBy;

    private String lastModifiedBy;
}
