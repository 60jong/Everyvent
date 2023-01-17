package com.app.everyvent.service;

import com.app.everyvent.domain.Event;
import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public void save(Event event) {
        eventRepository.save(event);
    }

    public int crawl(Airline airline) {
        List<Event> events = airline.crawlEvents();
        return saveNew(events);
    }

    public int saveNew(List<Event> events) {
        int newEvents = 0;
        for (Event event : events) {
            if (!isNew(event)) {
                continue;
            }
            save(event);
            newEvents++;
        }

        return newEvents;
    }

    public Boolean isNew(Event event) {
        if (eventRepository.existsByUrl(event.getUrl())) {
            return false;
        }

        return true;
    }
}
