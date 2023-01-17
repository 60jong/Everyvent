package com.app.everyvent.member.repository;


import com.app.everyvent.domain.Member;
import com.app.everyvent.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void joinMember() throws Exception {
        //given
        Member member = new Member("YKJ", "rudwhd515@gmail.com", "123456", "01049039081");

        //when
        memberRepository.save(member);

        //then
    }

}