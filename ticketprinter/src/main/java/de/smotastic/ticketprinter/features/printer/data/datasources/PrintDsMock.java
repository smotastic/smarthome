package de.smotastic.ticketprinter.features.printer.data.datasources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterResolution;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

@Slf4j
public class PrintDsMock implements PrintDs {

  static Optional<PrintService> locate() {
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    return Arrays.stream(printServices).filter(p -> p.getName().contains("Thermal")).findFirst();
  }


  void printImg(String imgPath) {
    PrintService service = locate().orElseThrow();
    try {
      DocPrintJob printJob = service.createPrintJob();
      FileInputStream fin = new FileInputStream(imgPath);
      DocAttributeSet das = new HashDocAttributeSet();
      das.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
      //das.add(new PrintQuality(3));
      Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.JPEG, das);
      PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
      pras.add(new Copies(1));
      pras.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
      printJob.print(doc, pras);
      fin.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public boolean print(String filePath) {
    log.info("Printing file: {}", filePath);
    String imgPath = filePath.replace(".pdf", ".jpg");
    try {
      PDDocument document = Loader.loadPDF(new File(filePath));
      PDFRenderer pdfRenderer = new PDFRenderer(document);
      BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

      ImageIOUtil.writeImage(bim, imgPath, 300);
      printImg(imgPath);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return true;
  }
}
