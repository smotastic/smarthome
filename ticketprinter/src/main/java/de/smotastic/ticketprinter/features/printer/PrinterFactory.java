package de.smotastic.ticketprinter.features.printer;

import de.smotastic.ticketprinter.features.printer.application.PrintController;
import io.swagger.api.PrintApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterFactory {


    @Bean
    public PrintApi printApi() {
        return new PrintController();
    }
}
