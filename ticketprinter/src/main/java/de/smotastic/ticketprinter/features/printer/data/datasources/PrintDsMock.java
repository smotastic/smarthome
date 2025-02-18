package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;

public class PrintDsMock implements PrintDs {
    @Override
    public boolean print(String filePath) {
        return true;
    }
}
