package com.app.flyvent.repository;

import com.app.flyvent.domain.Member;
import com.app.flyvent.domain.Subscription;
import com.app.flyvent.domain.destination.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    @Autowired
    private EntityManager em;

    public void save(Member newMember) {
        em.persist(newMember);
    }

    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    public Optional<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email")
                .setParameter("email", email)
                .getResultStream()
                .findAny();
    }

    public void saveSubscription(Subscription subscription) {
        em.persist(subscription);
    }

    public List<Subscription> findSubscriptions(Member member) {
        return em.createQuery("select s from Subscription s where s.member = :member", Subscription.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<Member> findAllByDestination(Destination destination) {
        return em.createQuery("select md.member from MemberDestination md where md.destination = :destination", Member.class)
                .setParameter("destination", destination)
                .getResultList();
    }
}
