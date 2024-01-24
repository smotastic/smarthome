package de.smotastic.radioalarm.shared;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.StatusAtLeastStrategy;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration
public class LogbookConfiguration {


    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .sink(new DefaultSink(new JsonHttpLogFormatter(), new DefaultHttpLogWriter()))
                .strategy(new StatusAtLeastStrategy(200))
                .build();
    }
}
