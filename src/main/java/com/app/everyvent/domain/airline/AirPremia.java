package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("APZ")
@NoArgsConstructor
public class AirPremia extends Airline {
    private static final String APZ_EVENT_URL = "https://www.airpremia.com/kr/ko/event/promotionList";

    public AirPremia(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
