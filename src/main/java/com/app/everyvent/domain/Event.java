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

    private String text;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor
    @Builder
    public Event(Airline airline, String url, String text, LocalDate startDate, LocalDate endDate) {
        this.airline = airline;
        this.url = url;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.airline.addEvent(this);
        setId();
    }


    // Getter

    // Method
    public void setId() {
        this.id = Long.valueOf(this.url.hashCode());
    }
}
