package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import com.app.everyvent.domain.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "code")
public abstract class Airline {

    @Id
    @GeneratedValue
    @Column(name = "airline_id")
    private Long id;

    @Column(name = "korean_name")
    private String koreanName;

    @Column(name = "english_name")
    private String englishName;

    private String country;

    @OneToMany(mappedBy = "airline")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "airline")
    private List<Subscription> subscriptions = new ArrayList<>();

    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor
    public Airline(String koreanName, String englishName, String country) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.country = country;
    }

    // Getter
    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    // Setter
    public void addEvent(Event event) {
        this.events.add(event);
    }
    // Method
    public void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    public abstract List<Event> crawlEvents();
}
