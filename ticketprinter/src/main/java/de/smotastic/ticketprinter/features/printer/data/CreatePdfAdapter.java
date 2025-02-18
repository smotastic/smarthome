package de.smotastic.ticketprinter.features.printer.data;

import de.smotastic.ticketprinter.features.printer.data.datasources.CreatePdfDs;
import de.smotastic.ticketprinter.features.printer.domain.CreatePdfPort;
import de.smotastic.ticketprinter.features.printer.domain.PrintEntity;
import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePdfAdapter implements CreatePdfPort {

    private final CreatePdfDs createPdfDs;

    @Override
    public PrintEntity create(TicketEntity ticket) {
        String filePath = createPdfDs.createPdf(ticket);
        return new PrintEntity(filePath);
    }
}
