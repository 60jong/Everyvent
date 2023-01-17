package com.app.everyvent.service;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public void register(Airline airline) {
        airlineRepository.save(airline);
    }

    public List<Airline> findAllById(List<Long> airlineIds) {
        List<Airline> airlines = new ArrayList<>();
        for (Long airlineId : airlineIds) {
            airlines.add(findById(airlineId));
        }

        return airlines;
    }

    public Airline findById(Long airlineId) {
        return airlineRepository.findById(airlineId);
    }
}
