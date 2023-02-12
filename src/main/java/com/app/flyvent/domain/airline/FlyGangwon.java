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
@DiscriminatorValue("FGW")
@NoArgsConstructor
public class FlyGangwon extends Airline {
    private static final String FGW_EVENT_URL = "https://flygangwon.com/ko/contents/event/viewEventList.do";
    private static final String FGW_EVENT_DETAIL_URL_PREFIX = "https://flygangwon.com/ko/contents/event/viewEventDetail.do?no=";

    public FlyGangwon(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = getWebDriver();
        driver.get(FGW_EVENT_URL);

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

    public void clickNextPageButton(WebDriver driver) throws InterruptedException {
        driver.findElement(By.className("next"))
                .click();

        waitPageLoad();
    }

    public int getPageCount(WebDriver driver) {
        return driver.findElement(By.className("paging"))
                .findElements(By.tagName("strong"))
                .size();
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        List<WebElement> eventBoxes = driver.findElement(By.id("eventList"))
                .findElements(By.tagName("li"));

        for (WebElement eventBox : eventBoxes) {
            events.add(makeEvent(eventBox));
        }

        return events;
    }

    public Event makeEvent(WebElement eventBox) {
        String eventUrl = FGW_EVENT_DETAIL_URL_PREFIX + getEventDetailSequence(eventBox);

        String thumbnailUrl = eventBox.findElement(By.className("thumb"))
                .findElement(By.tagName("img"))
                .getAttribute("src");

        String eventText = eventBox.findElement(By.className("tit"))
                .getText();

        EventPeriod eventPeriod = getEventPeriod(eventBox);

        return new Event(this, eventUrl, thumbnailUrl, eventText, eventPeriod.getStartDate(), eventPeriod.getEndDate());
    }

    public String getEventDetailSequence(WebElement eventBox) {
        return eventBox.findElement(
                        By.tagName("a"))
                .getAttribute("data-event-seq-no");
    }

    public EventPeriod getEventPeriod(WebElement eventBox) {
        // "yyyy.MM.dd - yyyy.MM.dd" 형식
        String dateText = eventBox.findElement(By.className("date"))
                .getText();

        LocalDate startDate = LocalDate.parse(dateText.split("-")[0]
                .trim()
                .replaceAll("[.]", "-"));
        LocalDate endDate = LocalDate.parse(dateText.split("-")[1]
                .trim()
                .replaceAll("[.]", "-"));

        return new EventPeriod(startDate, endDate);
    }
}
