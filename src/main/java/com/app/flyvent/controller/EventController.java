package com.app.flyvent.controller;

import com.app.flyvent.dto.web.NewEventsCount;
import com.app.flyvent.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/crawl")
    public NewEventsCount crawlEvents(@RequestParam("airline_id") Long airlineId) {
        int newEventCount = eventService.crawl(airlineId);

        return new NewEventsCount(newEventCount);
    }
}
