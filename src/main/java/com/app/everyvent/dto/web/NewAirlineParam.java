package com.app.everyvent.dto.web;

import com.app.everyvent.domain.airline.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewAirlineParam {
    private String koreanName;
    private String englishName;
    private String code;
    private String country;

    public Airline toAirline() {
        if (code.equals("KAL")) {
            return new KoreanAir(koreanName, englishName, country);
        }

        if (code.equals("AAL")) {
            return new AsisanaAir(koreanName, englishName, country);
        }

        if (code.equals("JJA")) {
            return new JejuAir(koreanName, englishName, country);
        }

        if (code.equals("JNA")) {
            return new JinAir(koreanName, englishName, country);
        }

        if (code.equals("TWB")) {
            return new TwayAir(koreanName, englishName, country);
        }

        if (code.equals("ASV")) {
            return new AirSeoul(koreanName, englishName, country);
        }

        if (code.equals("ABL")) {
            return new AirBusan(koreanName, englishName, country);
        }

        if (code.equals("APZ")) {
            return new AirPremia(koreanName, englishName, country);
        }

        if (code.equals("FGW")) {
            return new FlyGangwon(koreanName, englishName, country);
        }

        return null;
    }
}
