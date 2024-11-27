package de.smotastic.ticketprinter.features.printer.data;

import de.smotastic.ticketprinter.features.printer.data.datasources.FindTicketDs;
import de.smotastic.ticketprinter.features.printer.domain.FindTicketPort;
import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTicketAdapter implements FindTicketPort {
    private final FindTicketDs findTicketDs;

    @Override
    public TicketEntity by(String id) {
        return findTicketDs.by(id);
    }
}
