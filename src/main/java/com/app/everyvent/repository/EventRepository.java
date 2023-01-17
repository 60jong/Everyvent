package com.app.everyvent.repository;

import com.app.everyvent.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepository {
    private final EntityManager em;

    public void saveAll(List<Event> events) {
        for (Event event : events) {
            em.persist(event);
        }
    }

    public Optional<Event> findOneByUrl(String eventUrl) {
        List<Event> findEvents = em.createQuery("select e from Event e where e.url = :eventUrl", Event.class)
                .setParameter("eventUrl", eventUrl)
                .getResultList();

        if (findEvents.size() == 1) {
            return Optional.ofNullable(findEvents.get(0));
        }

        return Optional.empty();
    }

    public Boolean existsByUrl(String eventUrl) {
        return em.createQuery("select e from Event e where e.url = :eventUrl")
                .setParameter("eventUrl", eventUrl)
                .getResultList()
                .size() > 0;
    }

    public Long save(Event event) {
        em.persist(event);
        return event.getId();
    }
}
