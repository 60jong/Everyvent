package com.app.flyvent.domain.airline;

import com.app.flyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("ABL")
@NoArgsConstructor
public class AirBusan extends Airline {
    private static final String ABL_EVENT_URL = "https://www.airbusan.com/content/common/flynjoy/flyNEvent/";

    public AirBusan(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
