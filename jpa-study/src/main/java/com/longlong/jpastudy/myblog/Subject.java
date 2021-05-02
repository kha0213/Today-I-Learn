package com.longlong.jpastudy.myblog;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
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
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "id")
    private Set<Teacher> teacherList = new HashSet<>();

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

    public Subject(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teacherList size = " + (teacherList !=null ? teacherList.size():0)+
                ", students size = " + (students !=null ? students.size():0) +
                '}';
    }
}
