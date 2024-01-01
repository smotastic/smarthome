package de.smotastic.radioalarm.application;

import de.smotastic.radioalarm.RadioPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RadioTask {

    private final RadioPlayer radioPlayer;

    @Scheduled(cron = "0 30 5 * * MON-FRI")
    public void reportCurrentTime() {
        log.info("Good Morning!");
        radioPlayer.play();
    }
}
