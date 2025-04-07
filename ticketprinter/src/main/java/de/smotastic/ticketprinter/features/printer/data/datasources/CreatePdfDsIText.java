package de.smotastic.ticketprinter.features.printer.data.datasources;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class CreatePdfDsIText implements CreatePdfDs {

  String template = """
      <!DOCTYPE html>
             <style>
               .title {
                 font-size: 45pt;
                 font-weight: bold;
                 color: white;
                 background-color: black;
                 padding: 24px;
               }
      
               .description {
                 font-size: 24pt;
               }
             </style>
             <html lang="de">
             <head>
               <meta charset="UTF-8">
               <title>Ticket Printer</title>
             </head>
             <body>
             <div class="title">{{title}}</div>
             <hr/>
             <div class="description">{{description}}</div>
             <hr/>
             <div class="assignee">{{assignee}}</div>
             </body>
             </html>
      """;

  @Override
  public String createPdf(TicketEntity ticket) {
    try {
      ConverterProperties converterProperties = new ConverterProperties();
      String ticketTemplate = template
          .replace("{{title}}", ticket.title())
          .replace("{{description}}", ticket.description())
          .replace("{{assignee}}", ticket.assignee());
      String fileName = "ticket_" + ticket.id() + ".pdf";
      HtmlConverter.convertToPdf(ticketTemplate,
          new FileOutputStream(fileName),
          converterProperties);
      /*Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

      document.open();
      Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
      Chunk titleChunk = new Chunk(ticket.title(), font);
      Chunk descriptionChunk = new Chunk(ticket.description(), font);

      document.add(titleChunk);
      document.add(descriptionChunk);
      document.close();*/
      return fileName;
    } catch (FileNotFoundException e) {
      log.error("Error creating PDFs", e);
      throw new RuntimeException(e);
    }
  }
}
