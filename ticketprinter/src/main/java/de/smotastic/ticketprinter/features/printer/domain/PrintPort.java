package de.smotastic.ticketprinter.features.printer.domain;

public interface PrintPort {

    boolean print(TicketEntity ticket);

}
