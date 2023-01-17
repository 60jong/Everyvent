package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("JJA")
@NoArgsConstructor
public class JejuAir extends Airline {
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "chromedriver.exe";
    private static final String JJA_EVENT_URL = "https://www.jejuair.net/ko/event/event.do";

    public JejuAir(String koreanName, String englishName, String country) {
        super(koreanName, englishName, country);
    }

    @Override
    public List<Event> crawlEvents() {
        ChromeOptions options = crawlConfig();

        WebDriver driver = new ChromeDriver(options);

        List<Event> events = new ArrayList<>();
        try {
            driver.get(JJA_EVENT_URL);
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간

            // 4. 더보기 버튼 클릭
            while (true) {
                try {
                    WebElement more__button = driver.findElement(By.className("more__button"));
                    more__button.click();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    break;
                }
            }

            // 이벤트 선택
            List<WebElement> elements = driver.findElements(By.className("search-result__item"));

            for (WebElement element : elements) {
                String eventUrl = element.findElement(By.tagName("a")).getAttribute("href");
                String eventText = getEventText(element);
                String period = element.findElement(By.className("event-banner__date")).getText();

                LocalDate startDate = LocalDate.parse(period.split("~")[0].trim().replaceAll("[.]","-"));
                LocalDate endDate = LocalDate.parse(period.split("~")[1].trim().replaceAll("[.]","-"));

                events.add(new Event(this, eventUrl, eventText, startDate, endDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 5. 브라우저 종료
        }

        return events;
    }

    private ChromeOptions crawlConfig() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        return options;
    }

    private String getEventText(WebElement e) {
        String eventText = e.findElement(By.className("event-banner__text")).getText();
        String eventTitle = e.findElement(By.className("event-banner__title")).getText();
        return eventText + " " + eventTitle;
    }

}
