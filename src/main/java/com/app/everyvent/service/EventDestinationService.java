package com.app.everyvent.service;

import com.app.everyvent.domain.EventDestination;
import com.app.everyvent.repository.EventDestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventDestinationService {
    private final EventDestinationRepository eventDestinationRepository;

    public void save(EventDestination eventDestination) {
        eventDestinationRepository.save(eventDestination);
    }
}
