package com.app.everyvent.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.app.everyvent.constant.Country.*;

public enum Continent {
    ASIA("ASIA", List.of(KOREA, JAPAN, VIETNAM)),
    EUROPE("EUROPE", Collections.emptyList()),
    SOUTH_AMERICA("SOUTH AMERICA", Collections.emptyList()),
    NORTH_AMERICA("NORTH AMERICA", Collections.emptyList()),
    AFRICA("AFRICA", Collections.emptyList()),
    OCEANIA("OCEANIA", Collections.emptyList()),
    NONE("NONE", Collections.emptyList()),
    ;

    private String name;
    private List<Country> countries;

    Continent(String name, List<Country> countries) {

        this.name = name;
        this.countries = countries;
    }

    public static Continent findByCountry(Country country) {
        return Arrays.stream(Continent.values())
                .filter(continent -> continent.hasCountry(country))
                .findAny()
                .orElse(NONE);
    }

    public static Continent findByName(String name) {
        String upperName = name.toUpperCase();
        return Arrays.stream(Continent.values())
                .filter(continent -> continent.getName().equals(upperName))
                .findAny()
                .orElse(NONE);
    }

    public boolean hasCountry(Country country) {
        return countries.contains(country);
    }

    public String getName() {
        return this.name;
    }

    public boolean isNone() {
        return this.name.equals("NONE");
    }
}
