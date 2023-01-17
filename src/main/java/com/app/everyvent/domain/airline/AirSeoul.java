package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("ASV")
@NoArgsConstructor
public class AirSeoul extends Airline {
    private static final String ASV_EVENT_URL = "https://flyairseoul.com/CW/ko/ingEvent.do";

    public AirSeoul(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
