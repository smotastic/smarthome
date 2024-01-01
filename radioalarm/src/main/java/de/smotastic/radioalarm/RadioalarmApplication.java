package de.smotastic.radioalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RadioalarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadioalarmApplication.class, args);
	}

}
