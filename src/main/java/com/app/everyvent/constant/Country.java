package com.app.everyvent.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Country {
    /*---ASIA---*/
    KOREA("KOREA", List.of("SEOUL", "JEJU", "BUSAN")),
    JAPAN("JAPAN", List.of("TOKYO", "OSAKA")),
    VIETNAM("VIETNAM", List.of("HANOI", "DANANG")),

    /*---NORTH_AMERICA---*/
    USA("USA", Collections.emptyList()),
    CANADA("CANADA", Collections.emptyList()),

    /*---SOUTH_AMERICA---*/
    BRAZIL("BRAZIL", Collections.emptyList()),
    ARGENTINA("ARGENTINA", Collections.emptyList()),

    /*---AFRICA---*/
    EGYPT("EGYPT", Collections.emptyList()),
    GHANA("GHANA", Collections.emptyList()),

    /*---EUROPE---*/
    UK("UK", Collections.emptyList()),
    FRANCE("FRANCE", Collections.emptyList()),
    GERMANY("GERMANY", Collections.emptyList()),

    /*---OCEANIA---*/
    AUSTRALIA("AUSTRALIA", Collections.emptyList()),
    NONE("NONE", Collections.emptyList());

    private String name;
    private List<String> cities;

    Country(String name, List<String> cities) {
        this.name = name;
        this.cities = cities;
    }

    public static Country findByCity(String city) {
        String upperCityName = city.toUpperCase();

        return Arrays.stream(Country.values())
                .filter(country -> country.hasCity(upperCityName))
                .findAny()
                .orElse(NONE);
    }

    public static Country findByName(String name) {
        String upperName = name.toUpperCase();

        return Arrays.stream(Country.values())
                .filter(country -> country.getName().equals(upperName))
                .findAny()
                .orElse(NONE);
    }

    public boolean hasCity(String city) {
        return cities.contains(city);
    }

    public String getName() {
        return this.name;
    }

    public boolean isNone() {
        return this.name.equals("NONE");
    }
}
