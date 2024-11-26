package de.smotastic.ticketprinter.features.printer.application;

import io.swagger.api.PrintApi;
import io.swagger.model.ModelApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public class PrintController implements PrintApi{

    @Override
    public ResponseEntity<ModelApiResponse> printTicket(String ticketId) {
        return null;
    }
}
