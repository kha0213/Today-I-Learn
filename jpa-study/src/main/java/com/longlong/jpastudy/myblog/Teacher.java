package com.longlong.jpastudy.myblog;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-22.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Getter
@Setter
@ToString(exclude = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

    private String name;

    @ManyToOne
    private Subject subject;

    public Teacher(String name) {
        this.name = name;
    }

    public void addSubject(Subject subject) {
        setSubject(subject);
        Set<Teacher> teacherList = subject.getTeacherList();
        if(!teacherList.contains( subject )){
            teacherList.add(this);
        }
    }

}
