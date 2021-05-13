package com.study.awsboot.web.dto;

import com.study.awsboot.domain.posts.Posts;
import lombok.Builder;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-13.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;


    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
