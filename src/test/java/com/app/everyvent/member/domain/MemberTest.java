package com.app.everyvent.member.domain;

import com.app.everyvent.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    private EntityManager em;

    @Test
    public void makeMember() throws Exception {
        //given
        Member member = new Member("YKJ", "rudwhd515@gmail.com", "123456", "01049039081");
        //when
        em.persist(member);
        //then
    }

}