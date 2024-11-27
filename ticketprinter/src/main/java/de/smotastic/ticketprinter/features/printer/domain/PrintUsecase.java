package de.smotastic.ticketprinter.features.printer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PrintUsecase {

    private final FindTicketPort findTicketPort;
    private final PrintPort printPort;

    public boolean execute(String ticketId) {
        TicketEntity ticket = findTicketPort.by(ticketId);
        log.info("Found ticket {}", ticket);
        printPort.print(ticket);
        return true;
    }
}
