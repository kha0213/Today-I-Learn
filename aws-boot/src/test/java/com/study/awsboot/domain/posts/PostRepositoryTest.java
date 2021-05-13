package com.study.awsboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) //junit4 의 RunWith대신
@SpringBootTest
class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 타이틀";
        String content = "테스트 본문입니다.테스트 본문입니다.테스트 본문입니다.테스트 본문입니다.테스트 본문입니다.테스트 본문입니다.";
        String author = "tatujjang@gmail.com";

        Posts post1 = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        postsRepository.save(post1);

        //when
        List<Posts> postsList = postsRepository.findAll();

        //than
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
}