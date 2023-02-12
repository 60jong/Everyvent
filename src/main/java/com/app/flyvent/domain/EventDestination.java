package com.app.flyvent.domain;

import com.app.flyvent.domain.destination.Destination;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class EventDestination {
    @Id @GeneratedValue
    @Column(name = "event_destination_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    /*---Constructor---*/
    public EventDestination(Event event, Destination destination) {
        this.event = event;
        event.addEventDestination(this);
        this.destination = destination;
        destination.addEventDestination(this);
    }
}
