package com.app.everyvent.domain.destination;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class City {
    @Id @GeneratedValue
    @Column(name = "city_id")
    private Long id;

    private String englishName;

    private String koreanName;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
