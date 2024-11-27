package de.smotastic.ticketprinter.features.printer.data.datasources;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@RequiredArgsConstructor
@Slf4j
public class CreatePdfDsIText implements CreatePdfDs{
    @Override
    public String createPdf(TicketEntity ticket) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);

            document.add(chunk);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            log.error("Error creating PDFs", e);
        }

        return "";
    }
}
