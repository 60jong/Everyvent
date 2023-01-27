package com.app.everyvent.repository;

import com.app.everyvent.domain.destination.City;
import com.app.everyvent.domain.destination.Continent;
import com.app.everyvent.domain.destination.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DestinationRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Continent> findAllContinents() {
        return em.createQuery("select c from Continent c", Continent.class)
                .getResultList();
    }

    public List<Country> findAllCountries() {
        return em.createQuery("select c from Country c", Country.class)
                .getResultList();
    }

    public List<City> findAllCities() {
        return em.createQuery("select c from City c", City.class)
                .getResultList();
    }
}
