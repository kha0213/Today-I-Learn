package com.longlong.jpastudy.dto;

import lombok.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-20
 * Time: 오후 10:08
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryHierarchicalDto {

    private Long category_id;

    private String name;

    private int level;

    private String out_name;

    private Long parent_category_id;
}
