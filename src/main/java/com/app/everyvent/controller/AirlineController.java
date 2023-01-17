package com.app.everyvent.controller;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.dto.PostAirlineReq;
import com.app.everyvent.dto.PostAirlineRes;
import com.app.everyvent.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping("/new")
    public PostAirlineRes postAirline(@RequestBody PostAirlineReq postAirlineReq) {
        Airline airline = postAirlineReq.toAirline();
        airlineService.register(airline);

        return new PostAirlineRes(airline);
    }
}
