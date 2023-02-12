package com.app.flyvent.repository;

import com.app.flyvent.domain.airline.Airline;
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
        return em.find(Airline.class, airlineId);
    }
}
