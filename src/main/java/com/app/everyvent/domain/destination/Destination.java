package com.app.everyvent.domain.destination;

import com.app.everyvent.domain.EventDestination;
import com.app.everyvent.domain.MemberDestination;

import javax.persistence.*;
import java.util.List;

@Entity
public class Destination {
    @Id @GeneratedValue
    @Column(name = "destination_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "destination")
    private List<MemberDestination> memberDestinations;

    @OneToMany(mappedBy = "event")
    private List<EventDestination> eventDestinations;

    private Integer status = 1;
}
