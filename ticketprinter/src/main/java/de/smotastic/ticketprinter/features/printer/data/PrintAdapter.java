package de.smotastic.ticketprinter.features.printer.data;

import de.smotastic.ticketprinter.features.printer.data.datasources.PrintDs;
import de.smotastic.ticketprinter.features.printer.domain.PrintEntity;
import de.smotastic.ticketprinter.features.printer.domain.PrintPort;
import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrintAdapter implements PrintPort {

    private final PrintDs printDs;

    @Override
    public boolean print(PrintEntity printEntity) {
        return printDs.print(printEntity.filePath());
    }
}
