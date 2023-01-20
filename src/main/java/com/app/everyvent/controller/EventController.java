package com.app.everyvent.controller;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.dto.NewEventsCount;
import com.app.everyvent.service.AirlineService;
import com.app.everyvent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AirlineService airlineService;

    @PostMapping("/crawl/{airlineId}")
    public NewEventsCount crawlEvents(@PathVariable("airlineId") Long airlineId) {
        Airline airline = airlineService.findById(airlineId);
        int newEventCount = eventService.crawl(airline);

        return new NewEventsCount(newEventCount);
    }
}
