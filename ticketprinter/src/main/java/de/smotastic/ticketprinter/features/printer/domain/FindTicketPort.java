package de.smotastic.ticketprinter.features.printer.domain;

public interface FindTicketPort {

    TicketEntity by(String id);
}
