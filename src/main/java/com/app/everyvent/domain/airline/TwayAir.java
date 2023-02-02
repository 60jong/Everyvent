package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import com.app.everyvent.dto.EventPeriod;
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
@DiscriminatorValue("TWB")
@NoArgsConstructor
public class TwayAir extends Airline {
    private static final String TWB_EVENT_URL = "https://www.twayair.com/app/promotion/event/being";

    public TwayAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = super.getWebDriver();
        driver.get(TWB_EVENT_URL);

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

    public int getPageCount(WebDriver driver) {
        int pageElementCount = driver.findElement(By.cssSelector(".row.mb100"))
                .findElements(By.tagName("a"))
                .size();

        return pageElementCount - 4;
    }

    private void clickNextPageButton(WebDriver driver) throws InterruptedException {
        WebElement nextPageButton = driver.findElement(By.cssSelector(".bg.next"));
        nextPageButton.click();
        waitPageLoad();
    }


    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        // 이벤트 선택
        List<WebElement> eventBoxes = driver.findElement(By.className("evt_list"))
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
                        By.className("img"))
                .findElement(By.tagName("img"))
                .getAttribute("src");

        String eventText = eventBox.findElement(
                        By.className("img"))
                .findElement(By.tagName("img"))
                .getAttribute("alt");

        EventPeriod eventPeriod = getEventPeriod(eventBox);

        return new Event(this, eventUrl, thumbnailUrl, eventText, eventPeriod.getStartDate(), eventPeriod.getEndDate());
    }

    public EventPeriod getEventPeriod(WebElement element) {
        // "yyyy.MM.dd ~ yyyy.MM.dd" 형식
        String period = element.findElement(By.xpath("a/p[2]")).getText();

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
