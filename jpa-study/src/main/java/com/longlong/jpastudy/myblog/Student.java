package com.longlong.jpastudy.myblog;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-22.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Entity
@Getter
@Setter
@ToString(exclude = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private String name;

    @ManyToMany
    private Set<Subject> subjects = new HashSet<>();

    @OneToOne
    private Address address;

    public Student(String name) {
        this.name = name;
    }

    public void addSubject(Subject subject) {
        if(this.subjects != null) {
            this.subjects.remove(this);
        }
        subject.getStudents().add(this);
        subjects.add(subject);
    }
}
