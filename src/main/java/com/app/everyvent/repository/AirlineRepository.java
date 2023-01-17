package com.app.everyvent.repository;

import com.app.everyvent.domain.airline.Airline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AirlineRepository {
    private final EntityManager em;


    public void save(Airline airline) {
        em.persist(airline);
    }

    public Airline findById(Long airlineId) {
        return em.createQuery("select a from Airline a where a.id = :airlineId", Airline.class)
                .setParameter("airlineId", airlineId)
                .getSingleResult();
    }
}
