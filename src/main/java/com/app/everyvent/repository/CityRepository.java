package com.app.everyvent.repository;

import com.app.everyvent.domain.destination.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CityRepository {
    private final EntityManager em;

    public City findOne(Long cityId) {
        return em.find(City.class, cityId);
    }
}
