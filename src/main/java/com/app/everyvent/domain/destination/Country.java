package com.app.everyvent.domain.destination;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Country {
    @Id @GeneratedValue
    @Column(name = "country_id")
    private Long id;

    private String englishName;

    private String koreanName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();
}
