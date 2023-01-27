package com.app.everyvent.repository;

import com.app.everyvent.domain.destination.City;
import com.app.everyvent.domain.destination.Continent;
import com.app.everyvent.domain.destination.Country;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DestinationRepositoryTest {
    @Autowired
    DestinationRepository destinationRepository;

    @Test
    public void continent_조회() throws Exception {
        List<Continent> continents = destinationRepository.findAllContinents();
        System.out.println(continents.size());
        assertThat(continents.size()).isGreaterThan(0);
    }

    @Test
    public void country_조회() throws Exception {
        List<Country> countries = destinationRepository.findAllCountries();

        assertThat(countries.size()).isGreaterThan(0);
    }

    @Test
    public void city_조회() throws Exception {
        List<City> cities = destinationRepository.findAllCities();

        assertThat(cities.size()).isGreaterThan(0);
    }
}