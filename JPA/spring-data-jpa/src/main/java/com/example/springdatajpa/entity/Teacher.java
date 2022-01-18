package com.example.springdatajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import static javax.persistence.FetchType.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(
        name = "Teacher.findByName",
        query = "select t from Teacher as t where t.name = :name"
)
@NamedNativeQuery(
        name = "Teacher.findNativeByName",
        query = "select * from Teacher where name = :name",
        resultClass = Teacher.class
)
@NamedEntityGraph(name = "Teacher.subject",
        attributeNodes = @NamedAttributeNode("subject"))
public class Teacher extends BaseEntity {
    private static Log log = LogFactory.getLog(Teacher.class);
    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

    private String name;

    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Teacher(String name, int age, Subject subject) {
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    public Teacher(String name, int age) {
        this(name, age, null);
    }

    @PrePersist
    void prePersist() {
        log.info("😊 prePersist start!!!");
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        lastModifiedDate = now;
    }

    @PostPersist
    void postPersist() {
        log.info("😊 postPersist start!!!");
    }

    @PreRemove
    void preRemove() {
        log.info("😊 preRemove start!!!");
    }

    @PostRemove
    void postRemove() {
        log.info("😊 postRemove start!!!");
    }

    @PreUpdate
    void preUpdate() {
        log.info("😊 preUpdate start!!!");
        lastModifiedDate = LocalDateTime.now();
    }

    @PostUpdate
    void postUpdate() {
        log.info("😊 postUpdate start!!!");
    }

    @PostLoad
    void postLoad() {
        log.info("😊 postLoad start!!!");
    }
}
