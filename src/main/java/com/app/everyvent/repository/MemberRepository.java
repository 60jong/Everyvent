package com.app.everyvent.repository;

import com.app.everyvent.domain.Member;
import com.app.everyvent.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private EntityManager em;

    public void save(Member newMember) {
        em.persist(newMember);
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public void saveSubscription(Subscription subscription) {
        em.persist(subscription);
    }

    public List<Subscription> findSubscriptions(Member member) {
        return em.createQuery("select s from Subscription s where s.member = :member")
                .setParameter("member", member)
                .getResultList();
    }
}
