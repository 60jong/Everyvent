package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("FGW")
@NoArgsConstructor
public class FlyGangwon extends Airline {
    private static final String FGW_EVENT_URL = "https://flygangwon.com/ko/contents/event/viewEventList.do";

    public FlyGangwon(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
