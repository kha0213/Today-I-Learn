package com.study.querydsl.init;

import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitMemberData {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.initData();
    }

    @Component
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void initData() {
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);

            for (int i = 0; i < 100; i++) {
                Team selectTeam = i % 2 == 0 ? teamA : teamB;
                em.persist(new Member("member" + i, i, selectTeam));
            }
        }
    }
}
