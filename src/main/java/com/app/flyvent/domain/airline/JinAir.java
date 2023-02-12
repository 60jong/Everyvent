package com.app.flyvent.domain.airline;

import com.app.flyvent.domain.Event;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = super.getWebDriver();
        driver.get(JNA_EVENT_URL);
        waitPageLoad();

        driver.findElement(By.cssSelector("#btnConfirm")).click();

        return null;
    }
}
