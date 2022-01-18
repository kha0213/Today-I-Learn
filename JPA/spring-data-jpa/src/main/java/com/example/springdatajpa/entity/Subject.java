package com.example.springdatajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@ToString
@NoArgsConstructor
@Getter @Setter
@Entity
public class Subject {
    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String title;

    @ToString.Exclude
    @OneToMany(mappedBy = "subject")
    private List<Teacher> teacherList = new ArrayList<>();

    public Subject(String title) {
        this.title = title;
    }
}
