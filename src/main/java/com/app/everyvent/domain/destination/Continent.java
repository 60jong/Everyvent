package com.app.everyvent.domain.destination;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Continent {
    @Id @GeneratedValue
    @Column(name = "continent_id")
    private Long id;

    private String englishName;

    private String koreanName;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries = new ArrayList<>();

    @OneToMany(mappedBy = "continent")
    private List<City> cities = new ArrayList<>();
}
