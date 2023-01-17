package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("TWB")
@NoArgsConstructor
public class TwayAir extends Airline {
    private static final String TWB_EVENT_URL = "https://www.twayair.com/app/promotion/event/being";

    public TwayAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
