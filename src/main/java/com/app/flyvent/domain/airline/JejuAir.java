package com.app.flyvent.domain.airline;

import com.app.flyvent.domain.Event;
import com.app.flyvent.dto.EventPeriod;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("JJA")
@NoArgsConstructor
public class JejuAir extends Airline {
    private static final String JJA_EVENT_URL = "https://www.jejuair.net/ko/event/event.do";

    public JejuAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = getWebDriver();
        driver.get(JJA_EVENT_URL);

        waitPageLoad();

        clickMoreButtons(driver);

        List<Event> events = extractEvents(driver);

        endCrawl(driver);

        return events;
    }

    private void clickMoreButtons(WebDriver driver) throws InterruptedException {
        while (true) {
            try {
                WebElement more__button = driver.findElement(By.className("more__button"));
                more__button.click();
                waitPageLoad();
            } catch (NoSuchElementException exception) {
                // '더보기' 버튼이 없다면 반복문 탈출
                break;
            }
        }
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        List<WebElement> eventElements = driver.findElements(By.className("search-result__item"));

        for (WebElement eventElement : eventElements) {
            events.add(makeEvent(eventElement));
        }

        return events;
    }

    public Event makeEvent(WebElement element) {
        String eventUrl = element.findElement(By.tagName("a")).getAttribute("href");
        String thumbnailUrl = getThumbnailImgUrl(element);
        String eventText = getEventText(element);
        EventPeriod period = getEventPeriod(element);

        return new Event(this, eventUrl, thumbnailUrl, eventText, period.getStartDate(), period.getEndDate());
    }

    public String getThumbnailImgUrl(WebElement element) {
        String thumbnailUrlElement = element.findElement(
                By.className("event-banner-item"))
                .getAttribute("style");

        return thumbnailUrlElement.split("\"")[1];
    }

    public String getEventText(WebElement element) {
        String eventText = element.findElement(By.className("event-banner__text")).getText();
        String eventTitle = element.findElement(By.className("event-banner__title")).getText();
        return eventText + " " + eventTitle;
    }

    public EventPeriod getEventPeriod(WebElement element) {
        // "yyyy.MM.dd ~ yyyy.MM.dd" 형식
        String period = element.findElement(By.className("event-banner__date")).getText();

        LocalDate startDate = LocalDate.parse(
                period.split("~")[0]
                        .trim()
                        .replaceAll("[.]","-"));

        LocalDate endDate = LocalDate.parse(
                period.split("~")[1]
                        .trim()
                        .replaceAll("[.]","-"));

        return new EventPeriod(startDate, endDate);
    }

}
