package com.app.flyvent.member.domain;

import com.app.flyvent.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    private EntityManager em;

    @Test
    public void makeMember() throws Exception {
        //given
        Member member = new Member("YKJ", "rudwhd515@gmail.com", "123456", "01049039081", true);
        //when
        em.persist(member);
        //then
    }

}