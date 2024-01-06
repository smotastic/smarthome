package de.smotastic.radioalarm;

import de.smotastic.radioalarm.data.*;
import de.smotastic.radioalarm.domain.RadioPlayerPort;
import de.smotastic.radioalarm.domain.RadioPlayerUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadioPlayerFactory {
    @Bean
    public RadioStationDs radioStationDs() {
        return new RadioStationDsMock();
    }
    @Bean
    public RadioPlayerDs radioPlayerDs() {
        return new RadioPlayerDsJLayer();
    }
    @Bean
    public RadioPlayerPort radioPlayerPort(RadioStationDs radioStationDs, RadioPlayerDs radioPlayerDs) {
        return new RadioPlayerAdapter(radioPlayerDs, radioStationDs);
    }
    @Bean
    public RadioPlayerUsecase radioPlayerUsecase(RadioPlayerPort radioPlayerPort) {
        return new RadioPlayerUsecase(radioPlayerPort);
    }

}
