package com.app.everyvent.service;

import com.app.everyvent.domain.Event;
import com.app.everyvent.domain.EventType;
import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final DestinationService destinationService;

    public void save(Event event) {
        eventRepository.save(event);
    }

    public int crawl(Airline airline) {
        List<Event> events = Collections.emptyList();

        try {
            events = airline.crawlEvents();
            events.stream()
                    .forEach(event -> setEventType(event));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saveNew(events);
    }

    public void setEventType(Event event) {
        EventType eventType = EventType.ETC;

        if (destinationService.canFindDestinationIn(event.getText())) {
            eventType = EventType.TRAVEL;
        }

        event.setEventType(eventType);
    }

    public int saveNew(List<Event> events) {
        int newEvents = 0;
        for (Event event : events) {
            if (has(event)) {
                continue;
            }
            save(event);
            newEvents++;
        }

        return newEvents;
    }

    public boolean has(Event event) {
        if (eventRepository.existsByUrl(event.getUrl())) {
            return true;
        }

        return false;
    }
}
