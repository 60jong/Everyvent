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
@DiscriminatorValue("APZ")
@NoArgsConstructor
public class AirPremia extends Airline {
    private static final String APZ_EVENT_URL = "https://www.airpremia.com/kr/ko/event/promotionList";
    private static final String APZ_EVENT_DETAIL_PREFIX = "https://www.airpremia.com/event/promotionDetail?detailId=";

    public AirPremia(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }


    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = getWebDriver();

        driver.get(APZ_EVENT_URL);

        waitPageLoad();

        // 페이지 수 확인
        int pageCount = getPageCount(driver);

        // 이벤트 크롤링 후, 다음 페이지 클릭
        List<Event> events = new ArrayList<>();

        events.addAll(extractEvents(driver));

        for (int pageNum = 1; pageNum < pageCount; pageNum++) {
            clickNextPageButton(driver, pageNum + 1);
            events.addAll(extractEvents(driver));
        }

        return events;
    }

    public void clickNextPageButton(WebDriver driver, int nextPage) throws InterruptedException {
        driver.findElement(By.id("pagingDiv"))
                .findElement(By.linkText(String.valueOf(nextPage)))
                .click();
        waitPageLoad();
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        List<WebElement> eventBoxes = driver.findElement(By.className("promotionWrap"))
                .findElements(By.tagName("li"));

        for (WebElement eventBox : eventBoxes) {
            events.add(makeEvent(eventBox));
        }

        return events;
    }

    public Event makeEvent(WebElement element) {
        String eventUrl = APZ_EVENT_DETAIL_PREFIX + getEventDetailId(element);

        String thumbnailUrl = element.findElement(By.tagName("img"))
                .getAttribute("src");

        String eventText = element.findElement(By.className("badgeWrap"))
                .findElement(By.className("title"))
                .getText();

        EventPeriod period = getPeriod(element);

        return new Event(this, eventUrl, thumbnailUrl, eventText, period.getStartDate(), period.getEndDate());
    }

    public String getEventDetailId(WebElement element) {
        // "detailPage(__);" 형식
        String detailPageAttribute = element.findElement(By.className("imgRound"))
                .getAttribute("onClick");
        return detailPageAttribute.substring(11, detailPageAttribute.length() - 2);
    }

    public EventPeriod getPeriod(WebElement element) {
        // "yyyy-MM-dd ~ yyyy-MM-dd" 형식
        String period = element.findElement(By.className("ftG")).getText();

        LocalDate startDate = LocalDate.parse(
                period.split("~")[0]
                        .trim());

        LocalDate endDate = LocalDate.parse(
                period.split("~")[1]
                        .trim());

        return new EventPeriod(startDate, endDate);
    }

    public int getPageCount(WebDriver driver) {
        return driver.findElement(By.id("pagingDiv"))
                .findElements(By.tagName("li"))
                .size() - 2;
    }
}
