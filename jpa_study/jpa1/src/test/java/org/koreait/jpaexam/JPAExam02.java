package org.koreait.jpaexam;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class JPAExam02 {

    @Autowired
    private EntityManager em;

    @Test
    void ex01() {
        Member member = new Member();
        member.setUserId("user01");
        member.setUserNm("사용자01");
        member.setUserPw("123456");

        em.persist(member);
        em.flush();

        System.out.println(member);
    }
}
