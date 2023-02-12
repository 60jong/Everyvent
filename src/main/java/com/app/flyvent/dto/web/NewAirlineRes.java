package com.app.flyvent.dto.web;

import com.app.flyvent.domain.airline.Airline;
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
