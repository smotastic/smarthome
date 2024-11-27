package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;

public interface PrintDs {

    boolean print(TicketEntity ticket);
}
