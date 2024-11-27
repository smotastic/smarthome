package de.smotastic.ticketprinter.features.printer.domain;

public interface CreatePdfPort {

    PrintEntity create(TicketEntity ticket);
}
