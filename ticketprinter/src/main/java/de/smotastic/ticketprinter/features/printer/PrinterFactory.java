package de.smotastic.ticketprinter.features.printer;

import de.smotastic.ticketprinter.features.printer.data.FindTicketAdapter;
import de.smotastic.ticketprinter.features.printer.data.PrintAdapter;
import de.smotastic.ticketprinter.features.printer.data.datasources.*;
import de.smotastic.ticketprinter.features.printer.domain.FindTicketPort;
import de.smotastic.ticketprinter.features.printer.domain.PrintPort;
import de.smotastic.ticketprinter.features.printer.domain.PrintUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PrinterFactory {

//    @Bean
//    public FindTicketDs findTicketDs(Environment env) {
//        return new FindTicketDsTrello(env);
//    }

    @Bean
    public FindTicketDs findTicketDs() {
        return new FindTicketDsMock();
    }

    @Bean
    public FindTicketPort findTicketPort(FindTicketDs findTicketDs) {
        return new FindTicketAdapter(findTicketDs);
    }

    @Bean
    public PrintDs printDs() {
        return new PrintDsMock();
    }

    @Bean
    public PrintPort printPort(PrintDs printDs) {
        return new PrintAdapter(printDs);
    }

    @Bean
    public PrintUsecase printUsecase(FindTicketPort findTicketPort, PrintPort printPort) {
        return new PrintUsecase(findTicketPort, printPort);
    }


}
