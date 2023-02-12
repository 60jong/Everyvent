package com.app.flyvent.domain.destination;

import com.app.flyvent.domain.DestinationType;
import com.app.flyvent.domain.EventDestination;
import com.app.flyvent.domain.MemberDestination;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Destination {
    @Id @GeneratedValue
    @Column(name = "destination_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DestinationType destinationType;

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
    private List<MemberDestination> memberDestinations = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<EventDestination> eventDestinations = new ArrayList<>();

    private Integer status = 1;

    public void addEventDestination(EventDestination eventDestination) {
        this.eventDestinations.add(eventDestination);
    }

    public void addMemberDestination(MemberDestination memberDestination) {
        this.memberDestinations.add(memberDestination);
    }

    /*---Method---*/
    public boolean isContainedIn(String text) {
        if (destinationType.equals(DestinationType.CITY)) {
            return text.contains(city.getEnglishName()) ||
                    text.contains(city.getKoreanName());
        }

        if (destinationType.equals(DestinationType.COUNTRY)) {
            return text.contains(country.getEnglishName()) ||
                    text.contains(country.getKoreanName());
        }
        return text.contains(continent.getEnglishName()) ||
                text.contains(continent.getKoreanName());
    }

    public List<String> getDestinationNames() {
        List<String> destinationNames = new ArrayList<>();

        destinationNames.add(continent.getEnglishName());
        destinationNames.add(continent.getKoreanName());

        if (destinationType.equals(DestinationType.COUNTRY) || destinationType.equals(DestinationType.CITY)) {
            destinationNames.add(country.getEnglishName());
            destinationNames.add(country.getKoreanName());
        }

        if (destinationType.equals(DestinationType.CITY)) {
            destinationNames.add(city.getEnglishName());
            destinationNames.add(city.getKoreanName());
        }

        return destinationNames;
    }
}
