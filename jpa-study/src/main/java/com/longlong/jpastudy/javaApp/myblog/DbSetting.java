package com.longlong.jpastudy.javaApp.myblog;

import javax.persistence.EntityManager;

/**
 * Created by Kim Young Long.
 * Date : 2021-05-24.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public class DbSetting {
    public static void setting(EntityManager em) throws Exception {
        myblogSetting(em);
    }

    private static void myblogSetting(EntityManager em) {
        //과목 세팅
        Subject math = new Subject("math");
        em.persist(math);
        Subject english = new Subject("english");
        em.persist(english);
        Subject korean = new Subject("korean");

        // 선생님 세팅
        Teacher mathTeacherA = new Teacher("TmathA");
        mathTeacherA.addSubject(math);
        mathTeacherA.setClasses(Classes.ADVANCE);
        em.persist(mathTeacherA);

        Teacher mathTeacherB = new Teacher("TmathB");
        mathTeacherB.addSubject(math);
        mathTeacherB.setClasses(Classes.BASIC);
        em.persist(mathTeacherB);

        Teacher engTeacherA = new Teacher("TengA");
        engTeacherA.addSubject(english);
        engTeacherA.setClasses(Classes.ADVANCE);
        em.persist(engTeacherA);

        Teacher engTeacherB = new Teacher("TengB");
        engTeacherB.addSubject(english);
        engTeacherB.setClasses(Classes.BASIC);
        em.persist(engTeacherB);

        Teacher korTeacherA = new Teacher("TkorA");
        korTeacherA.addSubject(korean);
        korTeacherA.setClasses(Classes.ADVANCE);
        em.persist(korTeacherA);

        Teacher korTeacherB = new Teacher("TkorB");
        korTeacherB.addSubject(korean);
        korTeacherB.setClasses(Classes.BASIC);
        em.persist(korTeacherB);

        //학생 세팅
        for (int i = 0; i < 100; i++) {
            Student student = new Student("student" + i);
            if(i%2==0) student.addSubject(math);
            if(i%3==0) student.addSubject(english);
            if(i%5==0) student.addSubject(korean);
            em.persist(student);
        }

    }
}
