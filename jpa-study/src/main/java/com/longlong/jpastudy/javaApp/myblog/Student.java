package com.longlong.jpastudy.javaApp.myblog;

import com.longlong.jpastudy.vo.BaseEntity;
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
@TableGenerator(
        name = "Student_Generator",
        table = "JPA_Sequence",
        pkColumnValue = "Student_Seq",
        allocationSize = 1
)
@Getter
@Setter
@ToString(exclude = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "Student_Generator")
    @Column(name = "student_id")
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Classes classes;

    private int grades;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_SUBJECT",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    //@OneToOne
    //private Address address;

    public Student(String name) {
        this.name = name;
        this.grades = (int)(Math.random() * 100);
        this.classes = grades >= 50 ? Classes.ADVANCE : Classes.BASIC;
    }

    public void addSubject(Subject subject) {
        if(this.subjects != null) {
            this.subjects.remove(this);
        }
        subject.getStudents().add(this);
        subjects.add(subject);
    }
}
