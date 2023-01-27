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
@DiscriminatorValue("KAL")
@NoArgsConstructor
public class KoreanAir extends Airline {
    private static final String KAL_EVENT_URL = "https://www.koreanair.com/kr/ko/promotion/list";

    public KoreanAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() throws InterruptedException {
        WebDriver driver = super.getWebDriver();
        driver.get(KAL_EVENT_URL);

        waitPageLoad();

        // '1' 페이지 이벤트 crawl
        List<Event> events = extractEvents(driver);

        // 다음 페이지가 없을 때까지, 클릭 후 crawl
        while (hasNextEventPage(driver)) {
            clickNextPageButton(driver);
            events.addAll(extractEvents(driver));
        }

        endCrawl(driver);

        return events;
    }

    public boolean hasNextEventPage(WebDriver driver) {
        try {
            driver.findElement(By.cssSelector("pagination__item.-ctrl.-next"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private void clickNextPageButton(WebDriver driver) throws InterruptedException {
        WebElement nextPageButton = driver.findElement(By.cssSelector("pagination__item.-ctrl.-next"));
        nextPageButton.click();
        waitPageLoad();
    }

    public List<Event> extractEvents(WebDriver driver) {
        List<Event> events = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.className("landing__item"));

        for (WebElement element : elements) {
            events.add(makeEvent(element));
        }

        return events;
    }

    public Event makeEvent(WebElement element) {
        String eventUrl = element.findElement(By.tagName("a")).getAttribute("href");
        String thumbnailUrl = element.findElement(By.className("-img__pc")).getAttribute("src");
        String eventText = element.findElement(By.cssSelector("p.landing__txt")).getText();
        EventPeriod period = getPeriod(element);

        return new Event(this, eventUrl, thumbnailUrl, eventText, period.getStartDate(), period.getEndDate());
    }

    public EventPeriod getPeriod(WebElement element) {
        // "yyyy.MM.dd. ~ yyyy.MM.dd." 형식
        String period = element.findElement(By.cssSelector("p.landing__date")).getText();

        LocalDate startDate = LocalDate.parse(
                period.split("~")[1]
                        .trim()
                        .substring(0, 10)
                        .replaceAll("[.]", "-"));
        LocalDate endDate = LocalDate.parse(
                period.split("~")[1]
                        .trim()
                        .substring(0, 10)
                        .replaceAll("[.]","-"));

        return new EventPeriod(startDate, endDate);
    }
}
