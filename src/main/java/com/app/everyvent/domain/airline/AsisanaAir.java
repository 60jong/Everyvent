package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("AAL")
@NoArgsConstructor
public class AsisanaAir extends Airline {
    private static final String AAR_EVENT_URL = "https://flyasiana.com/C/KR/KO/event/index?menuId=CM201802220000728482";

    public AsisanaAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() {
        return null;
    }
}
