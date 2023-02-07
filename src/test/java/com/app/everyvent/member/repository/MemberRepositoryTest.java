package com.app.everyvent.member.repository;


import com.app.everyvent.domain.Member;
import com.app.everyvent.domain.MemberDestination;
import com.app.everyvent.domain.destination.Destination;
import com.app.everyvent.repository.DestinationRepository;
import com.app.everyvent.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DestinationRepository destinationRepository;

    @Test
    public void joinMember() throws Exception {
        //given
        Member member = createMember();
        //when
        memberRepository.save(member);

        //then
    }

    @Test
    public void 여행지로_멤버찾기() throws Exception {
        //given
        Member member1 = createMember();
        Member member2 = createMember();
        Destination destination = destinationRepository.findAll().get(0);

        //when
        em.persist(member1);
        em.persist(member2);
        em.persist(new MemberDestination(member1, destination));
        em.persist(new MemberDestination(member2, destination));


        //then
        assertThat(memberRepository.findAllByDestination(destination)).contains(member1, member2);
    }

    Member createMember() {
        return new Member("YKJ", "rudwhd515@gmail.com", "123456", "01049039081", true);
    }
}