package de.smotastic.ticketprinter.features.printer.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrintUsecase {

    private final FindTicketPort findTicketPort;
    private final PrintPort printPort;

    public boolean execute(String ticketId) {
        TicketEntity ticket = findTicketPort.by(ticketId);
        printPort.print(ticket);
        return true;
    }
}
