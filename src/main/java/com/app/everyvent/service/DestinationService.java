package com.app.everyvent.service;

import com.app.everyvent.domain.destination.Destination;
import com.app.everyvent.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public List<Destination> findAllById(List<Long> destinationIds) {
        return destinationIds.stream()
                .map(destinationId -> destinationRepository.findById(destinationId))
                .collect(Collectors.toList());
    }

    public boolean canFindDestinationIn(String text) {
        String uppercaseText = text.toUpperCase();

        List<Destination> allDestinations = destinationRepository.findAll();
        List<String> allDestinationNames = getAllDestinationNames(allDestinations);

        return allDestinationNames.stream()
                .filter(destinationName -> uppercaseText.contains(destinationName))
                .count() > 0;
    }

    public List<String> getAllDestinationNames(List<Destination> destinations) {
        List<String> allDestinationNames = new ArrayList<>();

        for (Destination destination : destinations) {
            allDestinationNames.addAll(destination.getDestinationNames());
        }

        return allDestinationNames;
    }

    public List<Destination> findDestinationsIn(String text) {
        String uppercaseText = text.toUpperCase();

        List<Destination> allDestinations = destinationRepository.findAll();
        return allDestinations.stream()
                .filter(destination -> destination.isContainedIn(uppercaseText))
                .collect(Collectors.toList());
    }
}
