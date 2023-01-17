package com.app.everyvent.dto;

import com.app.everyvent.domain.airline.Airline;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostAirlineRes {
    private String koreanName;
    private String englishName;
    private String country;

    public PostAirlineRes(Airline airline) {
        this.koreanName = airline.getKoreanName();
        this.englishName = airline.getEnglishName();
        this.country = airline.getCountry();
    }
}
