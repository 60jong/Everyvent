package com.app.everyvent.domain;

import com.app.everyvent.domain.destination.Destination;

import javax.persistence.*;

@Entity
public class EventDestination {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    private String continent;

    private String country;

    private String city;

    /*---Constructor---*/
    public EventDestination(Event event, Destination destination, String continent, String country, String city) {
        this.event = event;
        this.destination = destination;
        this.continent = continent;
        this.country = country;
        this.city = city;
    }
}
