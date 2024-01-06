package de.smotastic.radioalarm.application;

import de.smotastic.radioalarm.domain.RadioPlayerUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RadioTask {

    private final RadioPlayerUsecase radioPlayer;

    @Scheduled(cron = "0 30 5 * * MON-FRI")
    public void reportCurrentTime() {
        log.info("Good Morning!");
        radioPlayer.play(1L);
    }
}
