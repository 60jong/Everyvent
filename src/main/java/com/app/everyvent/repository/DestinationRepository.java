package com.app.everyvent.repository;

import com.app.everyvent.domain.destination.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DestinationRepository {
    @Autowired
    private EntityManager em;

    public void save(Destination destination) {
        em.persist(destination);
    }
}
