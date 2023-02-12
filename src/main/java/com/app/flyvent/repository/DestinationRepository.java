package com.app.flyvent.repository;

import com.app.flyvent.domain.destination.Destination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DestinationRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Destination> findAll() {
        return em.createQuery("select d from Destination d", Destination.class)
                .getResultList();
    }

    public Destination findById(Long destinationId) {
        return em.find(Destination.class, destinationId);
    }
}
