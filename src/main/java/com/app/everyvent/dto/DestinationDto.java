package com.app.everyvent.dto;

import com.app.everyvent.constant.Continent;
import com.app.everyvent.constant.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {
    private Continent continent;
    private Country country;
    private String city;

    public static DestinationDto of(String keyword) {
        keyword = keyword.toUpperCase();
        if (isContinent(keyword)) {
            return new DestinationDto(Continent.findByName(keyword), null, null);
        }

        if (isCountry(keyword)) {
            Country country = Country.findByName(keyword);
            Continent continent = Continent.findByCountry(country);
            return new DestinationDto(continent, country, null);
        }

        Country country = Country.findByCity(keyword);
        Continent continent = Continent.findByCountry(country);
        return new DestinationDto(continent, country, keyword);
    }

    public static boolean isContinent(String keyword) {
        return !Continent.findByName(keyword).isNone();
    }

    public static boolean isCountry(String keyword) {
        return !Country.findByName(keyword).isNone();
    }
}
