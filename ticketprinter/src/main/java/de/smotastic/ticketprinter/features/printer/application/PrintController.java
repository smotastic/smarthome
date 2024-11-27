package de.smotastic.ticketprinter.features.printer.application;

import de.smotastic.ticketprinter.features.printer.domain.PrintUsecase;
import io.swagger.api.V1Api;
import io.swagger.model.PrintResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PrintController implements V1Api {

    private final PrintUsecase printUsecase;

    @Override
    public ResponseEntity<PrintResponse> printTicket(String ticketId) {
        boolean execute = printUsecase.execute(ticketId);
        if(execute) {
            PrintResponse body = new PrintResponse();
            return ResponseEntity.ok(body.message("OK"));
        }
        return null;
    }
}
