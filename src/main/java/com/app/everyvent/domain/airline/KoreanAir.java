package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("KAL")
@NoArgsConstructor
public class KoreanAir extends Airline {
    private static final String KAL_EVENT_URL = "https://www.koreanair.com/kr/ko/promotion/main";

    public KoreanAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
