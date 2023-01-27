package com.app.everyvent.service;

import com.app.everyvent.domain.destination.City;
import com.app.everyvent.domain.destination.Continent;
import com.app.everyvent.domain.destination.Country;
import com.app.everyvent.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public boolean canFindDestinationIn(String text) {
        String uppercaseText = text.toUpperCase();

        List<Continent> continents = destinationRepository.findAllContinents();
        List<Country> countries = destinationRepository.findAllCountries();
        List<City> cities = destinationRepository.findAllCities();

        return (continents.stream()
                .filter(continent -> uppercaseText.contains(continent.getEnglishName())
                        || uppercaseText.contains(continent.getKoreanName()))
                .count()
                + countries.stream()
                .filter(country -> uppercaseText.contains(country.getEnglishName())
                        || uppercaseText.contains(country.getKoreanName()))
                .count()
                + cities.stream()
                .filter(city -> uppercaseText.contains(city.getEnglishName())
                        || uppercaseText.contains(city.getKoreanName()))
                .count()) > 0;
    }
}
