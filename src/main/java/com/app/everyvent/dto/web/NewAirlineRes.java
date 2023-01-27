package com.app.everyvent.dto.web;

import com.app.everyvent.domain.airline.Airline;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewAirlineRes {
    private String koreanName;
    private String englishName;
    private String country;

    public NewAirlineRes(Airline airline) {
        this.koreanName = airline.getKoreanName();
        this.englishName = airline.getEnglishName();
        this.country = airline.getCountry();
    }
}
