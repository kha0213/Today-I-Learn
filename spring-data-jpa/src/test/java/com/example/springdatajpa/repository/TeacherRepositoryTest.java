package com.example.springdatajpa.repository;

import com.example.springdatajpa.dto.TeacherDto;
import com.example.springdatajpa.entity.Subject;
import com.example.springdatajpa.entity.Teacher;
import org.assertj.core.api.Assertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.*;

@EnableJpaRepositories
@Transactional
@SpringBootTest
@Rollback(value = false)
class TeacherRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    TeacherRepository teacherRepository;

    @BeforeEach
    void settingDB() {
        Subject math = new Subject("math");
        em.persist(math);
        Subject english = new Subject("english");
        em.persist(english);
        Subject korean = new Subject("korean");
        em.persist(korean);

        Teacher teacherA = new Teacher("teacherA",50,math);
        teacherRepository.save(teacherA);
        List<Teacher> saveList = new ArrayList<>();
        Teacher teacherB = new Teacher("teacherB",33,english);
        saveList.add(teacherB);
        Teacher teacherC = new Teacher("mathT",42,math);
        saveList.add(teacherC);
        teacherRepository.saveAll(saveList);
    }

    @Test
    void 목록조회() {
        List<Teacher> teachers = teacherRepository.findAll();
        assertThat(teachers.size()).isEqualTo(3);
    }

    @Test
    void 이름으로찾기() {
        List<Teacher> teachers = teacherRepository.findByNameContains("teacher");
        System.out.println("teacherRepository = " + teacherRepository.getClass().getName());
        for (Teacher teacher : teachers) {
            System.out.println("teacher = " + teacher);
        }
        assertThat(teachers.size()).isEqualTo(2);
    }

    @Test
    void namedQuery() {
        List<Teacher> teachers = teacherRepository.findByName("teacherA");
        assertThat(teachers.size()).isEqualTo(1);
        assertThat(teachers.get(0).getName()).isEqualTo("teacherA");
    }

    @Test
    void namedNativeQuery() {
        List<Teacher> teachers = teacherRepository.findNativeByName("teacherA");
        assertThat(teachers.size()).isEqualTo(1);
        assertThat(teachers.get(0).getName()).isEqualTo("teacherA");
    }

    @Test
    void namedQueryInJpa() {
        List<Teacher> teachers = em.createNamedQuery("Teacher.findByName",Teacher.class)
                .setParameter("name","teacherA")
                .getResultList();
        assertThat(teachers.size()).isEqualTo(1);
        assertThat(teachers.get(0).getName()).isEqualTo("teacherA");
    }

    @Test
    void namedNativeQueryInJpa() {
        List<Teacher> teachers = em.createNativeQuery("select * from Teacher where name = :name",Teacher.class)
                .setParameter("name","teacherA")
                .getResultList();
        assertThat(teachers.size()).isEqualTo(1);
    }
    
    @Test
    void findQueryByAgeGreaterThanTest() {
        List<Teacher> teachers = teacherRepository.findQueryByAgeGreaterThan(40);
        assertThat(teachers.size()).isEqualTo(2);
    }

    @Test
    void findPageAllBy() {
        teacherRepository.deleteAll();
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teachers.add(new Teacher("tea"+i, i));
        }
        teacherRepository.saveAllAndFlush(teachers);
        PageRequest pr = PageRequest.of(2, 5, Sort.by("age").ascending());
        Page<Teacher> teacherPage = teacherRepository.findPageAllBy(pr);

        System.out.println("teacherPage = " + teacherPage.getTotalElements());
        System.out.println("getTotalPages() = " + teacherPage.getTotalPages());
        for (Teacher teacher : teacherPage.getContent()) {
            System.out.println("teacher = " + teacher);
        }
    }

    @Test
    void sliceTest() {
        teacherRepository.deleteAll();
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teachers.add(new Teacher("tea"+i, i));
        }
        teacherRepository.saveAllAndFlush(teachers);
        PageRequest pr = PageRequest.of(3, 4, Sort.by(ASC,"age"));
        Slice<Teacher> teacherSlice = teacherRepository.findSliceAllBy(pr);
        Slice<TeacherDto> dtos = teacherSlice.map(t ->  new TeacherDto(t.getName(),t.getAge(),""));
        for (TeacherDto dto : dtos) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    void bulkQuery() {
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            System.out.println("teacher1 = " + teacher);
        }

        teacherRepository.updateBulkAge(10);
        em.clear();
        List<Teacher> teachers2 = teacherRepository.findAll();
        for (Teacher teacher : teachers2) {
            System.out.println("teacher2 = " + teacher);
        }
    }

    @Test
    void entityGraph() {
        teacherRepository.deleteAll();
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teachers.add(new Teacher("tea"+i, i));
        }
        teacherRepository.findFirstByAgeIsLessThan(40);

        Teacher teacher = teachers.get(0);
        // Hibernate 지연 여부 확인
        boolean initialized = Hibernate.isInitialized(teacher.getSubject());
        System.out.println("[Hibernate] subject is proxy? " + initialized);

        // JPA 표준방법으로 확인
        PersistenceUnitUtil util = em.getEntityManagerFactory().getPersistenceUnitUtil();
        boolean loaded = util.isLoaded(teacher.getSubject());
        System.out.println("[Hibernate] subject is proxy? " + loaded);

        assertThat(initialized).isEqualTo(true);
        assertThat(loaded).isEqualTo(true);
    }

    @Test
    void namedEntityGraph() {
        Optional<Teacher> teacher = teacherRepository.findFirstBy();
        boolean initialized = Hibernate.isInitialized(teacher.get().getSubject());
        assertThat(initialized).isEqualTo(true);
    }

    @Test
    void findReadOnlyById() {
        Teacher teacher = new Teacher("test", 22);
        teacherRepository.save(teacher);
        em.flush();
        em.clear();
        Optional<Teacher> hintTea = teacherRepository.findHintById(teacher.getId());
        hintTea.get().setAge(33);
        em.flush();
        em.clear();
        Optional<Teacher> hintById = teacherRepository.findHintById(teacher.getId());
        assertThat(hintById.get().getAge()).isEqualTo(22); // ReadOnly라 변경되지 않음

    }

    @Test
    void findLockByName() {
        Optional<Teacher> teacherA = teacherRepository.findLockByName("teacherA");
        System.out.println("teacherA = " + teacherA.get());
    }

    @Test
    void findCustomRepo() {
        Teacher teacher = new Teacher("test", 22);
        teacherRepository.save(teacher);
        em.flush();
        em.clear();
        Teacher customById = teacherRepository.findCustomById(teacher.getId());
        System.out.println("customById = " + customById);
    }

    @Test
    void lifecycleTest () {
        System.out.println("😊 lifecycleTest start!!");
        Teacher teacher = new Teacher("testT", 100);
        teacherRepository.save(teacher);
        em.flush();
        em.clear();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Teacher findTeacher = teacherRepository.findById(teacher.getId()).get();
        findTeacher.setName("testT2");
        em.flush();
        em.clear();
    }

}