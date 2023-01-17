package com.app.everyvent;

import com.app.everyvent.domain.airline.JejuAir;
import com.app.everyvent.domain.Event;

import java.util.List;

public class WebCrawlerTest {

    public static void main(String[] args) {
        JejuAir jejuAir = new JejuAir();
        List<Event> events = jejuAir.crawlEvents();
        for (Event event : events) {
            System.out.println(event.getId());
        }
    }
}