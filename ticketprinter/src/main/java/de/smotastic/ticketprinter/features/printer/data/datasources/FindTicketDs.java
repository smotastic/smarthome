package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;

public interface FindTicketDs {

    TicketEntity by(String id);
}
