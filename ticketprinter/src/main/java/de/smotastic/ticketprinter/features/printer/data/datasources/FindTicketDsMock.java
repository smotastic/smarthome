package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;

public class FindTicketDsMock implements FindTicketDs {
    @Override
    public TicketEntity by(String id) {
        return TicketEntity.builder()
                .id(id)
                .title("Hallo Welt")
                .description("Wie geht es")
                .assignee("Ich")
                .build();
    }
}
