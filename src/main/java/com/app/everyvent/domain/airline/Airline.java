package com.app.everyvent.domain.airline;

import com.app.everyvent.domain.Event;
import com.app.everyvent.domain.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "code")
public abstract class Airline {
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "chromedriver.exe";

    @Id
    @GeneratedValue
    @Column(name = "airline_id")
    private Long id;

    @Column(name = "korean_name")
    private String koreanName;

    @Column(name = "english_name")
    private String englishName;

    private String country;

    @OneToMany(mappedBy = "airline")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "airline")
    private List<Subscription> subscriptions = new ArrayList<>();

    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor
    public Airline(String koreanName, String englishName, String country) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.country = country;
    }

    // Getter
    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    // Setter
    public void addEvent(Event event) {
        this.events.add(event);
    }
    // Method
    public void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    public WebDriver getWebDriver() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        return new ChromeDriver(options);
    }

    public void waitPageLoad() throws InterruptedException {
        Thread.sleep(2000);
    }

    public abstract List<Event> crawlEvents() throws InterruptedException;
}
