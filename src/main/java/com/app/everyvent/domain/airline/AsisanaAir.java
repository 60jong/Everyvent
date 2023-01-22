package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import com.app.everyvent.domain.Period;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = getWebDriver();

        driver.get(AAR_EVENT_URL);

        waitPageLoad();

        List<Event> events = extractEvents(driver);

        endCrawl(driver);

        return events;
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        // 이벤트 선택
        List<WebElement> eventBoxes = driver.findElement(By.className("list_thumb_type01"))
                .findElements(By.tagName("li"));

        for (WebElement eventBox : eventBoxes) {
            events.add(makeEvent(eventBox));
        }

        return events;
    }

    public Event makeEvent(WebElement eventBox) {
        String eventUrl = eventBox.findElement(
                By.tagName("a"))
                .getAttribute("href");

        String thumbnailUrl = eventBox.findElement(
                By.className("thumb"))
                .findElement(By.tagName("img"))
                .getAttribute("src");

        String eventText = eventBox.findElement(
                By.className("list_info"))
                .findElement(
                        By.className("title"))
                .getText();

        Period eventPeriod = getEventPeriod(eventBox);

        return new Event(this, eventUrl, thumbnailUrl, eventText, eventPeriod.getStartDate(), eventPeriod.getEndDate());
    }

    public Period getEventPeriod(WebElement element) {
        // "yyyy.MM.dd ~ yyyy.MM.dd" 형식
        String period = element.findElement(By.className("date")).getText();

        LocalDate startDate = LocalDate.parse(
                period.split("~")[0]
                        .trim()
                        .replaceAll("[.]","-"));

        LocalDate endDate = LocalDate.parse(
                period.split("~")[1]
                        .trim()
                        .replaceAll("[.]","-"));

        return new Period(startDate, endDate);
    }
}
