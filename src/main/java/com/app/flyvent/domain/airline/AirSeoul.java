package com.app.flyvent.domain.airline;

import com.app.flyvent.domain.Event;
import com.app.flyvent.dto.EventPeriod;
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
@DiscriminatorValue("ASV")
@NoArgsConstructor
public class AirSeoul extends Airline {
    private static final String ASV_EVENT_URL = "https://flyairseoul.com/CW/ko/ingEvent.do";

    public AirSeoul(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = getWebDriver();
        driver.get(ASV_EVENT_URL);

        waitPageLoad();

        // 페이지 수 확인
        int pageCount = getPageCount(driver);

        // 이벤트 크롤링 후, 다음 페이지 클릭
        List<Event> events = new ArrayList<>();
        for (int pageNum = 1; pageNum <= pageCount; pageNum++) {
            events.addAll(extractEvents(driver));
            clickNextPageButton(driver);
        }

        return events;
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        List<WebElement> eventBoxes = driver.findElement(By.className("event_list"))
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
                        By.className("event_img"))
                .findElement(By.tagName("img"))
                .getAttribute("src");

        String eventSubject = eventBox.findElement(
                        By.className("event_content"))
                .findElement(
                        By.className("noti"))
                .getText();

        String eventText = eventBox.findElement(
                        By.className("event_content"))
                .findElement(
                        By.className("txt"))
                .getText();

        EventPeriod eventPeriod = getEventPeriod(eventBox);

        return new Event(this, eventUrl, thumbnailUrl, eventSubject +"\n"+ eventText, eventPeriod.getStartDate(), eventPeriod.getEndDate());
    }

    public EventPeriod getEventPeriod(WebElement eventBox) {
        // " 이벤트 기간 yyyy.MM.dd ~ yyyy.MM.dd " 형식
        String dateText = eventBox.findElement(By.className("date")).getText();

        LocalDate startDate = LocalDate.parse(dateText.split(" ")[2]
                                                    .trim()
                                                    .replaceAll("[.]", "-"));
        LocalDate endDate = LocalDate.parse(dateText.split(" ")[4]
                                                    .trim()
                                                    .replaceAll("[.]", "-"));

        return new EventPeriod(startDate, endDate);
    }

    public void clickNextPageButton(WebDriver driver) {
        driver.findElement(By.className("next"))
                .click();
    }

    public int getPageCount(WebDriver driver) {
        return driver.findElement(By.className("page_navi"))
                .findElements(By.tagName("li"))
                .size();
    }
}
