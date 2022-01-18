package com.example.springdatajpa.repository;

import com.example.springdatajpa.dto.TeacherDto;
import com.example.springdatajpa.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, CustomRepository {
    Teacher findById(long id);
    List<Teacher> findByNameContains(String name);

    //NamedQuery
    List<Teacher> findByName(@Param("name") String name);

    //NamedNativeQuery
    List<Teacher> findNativeByName(@Param("name") String name);

    @Query("select t from Teacher as t where t.age > :age")
    List<Teacher> findQueryByAgeGreaterThan(@Param("age") int age);

    //get Page
    Page<Teacher> findPageAllBy(Pageable pageable);

    //get Slice , Dto
    Slice<Teacher> findSliceAllBy(Pageable pageable);

    // 벌크성 수정 쿼리
    @Modifying(clearAutomatically = true)
    @Query(value = "update Teacher as t set t.age = :age", nativeQuery = false)
    int updateBulkAge(@Param("age") int age);

    //EntityGraph
    @EntityGraph(attributePaths = {"subject"})
    List<Teacher> findFirstByAgeIsLessThan(int age);

    @EntityGraph(value = "Teacher.subject")
    Optional<Teacher> findFirstBy();

    @QueryHints(value = { @QueryHint(name = "org.hibernate.readOnly", value = "true")})
    Optional<Teacher> findHintById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Teacher> findLockByName(String name);

}
