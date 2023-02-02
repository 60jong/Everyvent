package com.app.everyvent.domain;


import com.app.everyvent.domain.airline.Airline;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Event {
    @Id
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    private String url;

    private String thumbnailUrl;

    private String text;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventDestination> eventDestinations = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*---Setter---*/
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    // Constructor
    @Builder
    public Event(Airline airline, String url, String thumbnailUrl, String text, LocalDate startDate, LocalDate endDate) {
        this.airline = airline;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.airline.addEvent(this);
        setId();
    }

    // Method
    public void setId() {
        this.id = Long.valueOf(this.url.hashCode());
    }

    public void addEventDestination(EventDestination eventDestination) {
        this.eventDestinations.add(eventDestination);
    }

    public boolean isTypeOf(EventType eventType) {
        return this.eventType.equals(eventType);
    }
}
