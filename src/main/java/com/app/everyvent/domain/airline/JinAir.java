package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("JNA")
@NoArgsConstructor
public class JinAir extends Airline {
    private static final String JNA_EVENT_URL = "https://www.jinair.com/promotion/nowLeave";

    public JinAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
