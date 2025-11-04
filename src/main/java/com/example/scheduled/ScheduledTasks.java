package com.example.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 60000) // выполняется каждые 60 секунд
    public void reportCurrentTime() {
        LocalTime chisinauTime = LocalTime.now(ZoneId.of("Europe/Chisinau"));
        String formattedTime = chisinauTime.format(formatter);
        log.info("1. The time is now {}", formattedTime);
    }

    // С фиксированной задержкой между завершением и началом следующего
    @Scheduled(fixedDelay = 60000)
    public void withDelay() {
        LocalTime chisinauTime = LocalTime.now(ZoneId.of("Europe/Chisinau"));
        String formattedTime = chisinauTime.format(formatter);
        log.info("2. The time is now {}", formattedTime);
    }

    // По cron выражению (каждую минуту)
    @Scheduled(cron = "0 * * * * *")
    public void everyMinuteCron() {
        LocalTime chisinauTime = LocalTime.now(ZoneId.of("Europe/Chisinau"));
        String formattedTime = chisinauTime.format(formatter);
        log.info("3. The time is now {}", formattedTime);
    }

}
