package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;

public interface CreatePdfDs {
    String createPdf(TicketEntity ticket);
}
