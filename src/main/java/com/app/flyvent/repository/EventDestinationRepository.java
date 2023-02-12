package com.app.flyvent.repository;

import com.app.flyvent.domain.EventDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EventDestinationRepository {
    @Autowired
    private EntityManager em;

    public void save(EventDestination eventDestination) {
        em.persist(eventDestination);
    }


}
