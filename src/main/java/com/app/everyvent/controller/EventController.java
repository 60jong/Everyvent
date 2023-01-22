package com.app.everyvent.controller;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.dto.NewEventsCount;
import com.app.everyvent.service.AirlineService;
import com.app.everyvent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AirlineService airlineService;

    @PostMapping("/crawl")
    public NewEventsCount crawlEvents(@RequestParam("airline_id") Long airlineId) {
        Airline airline = airlineService.findById(airlineId);
        int newEventCount = eventService.crawl(airline);

        return new NewEventsCount(newEventCount);
    }
}
