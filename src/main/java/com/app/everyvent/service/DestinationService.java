package com.app.everyvent.service;

import com.app.everyvent.domain.destination.City;
import com.app.everyvent.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final CityRepository cityRepository;

    public City findCity(Long cityId) {
        return cityRepository.findOne(cityId);
    }

    public List<City> findCities(List<Long> cityIds) {
        List<City> cities = new ArrayList<>();
        for (Long cityId : cityIds) {
            cities.add(cityRepository.findOne(cityId));
        }

        return cities;
    }
}
