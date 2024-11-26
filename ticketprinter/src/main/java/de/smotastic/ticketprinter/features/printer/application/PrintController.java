package de.smotastic.ticketprinter.features.printer.application;

import de.smotastic.ticketprinter.features.printer.domain.PrintUsecase;
import io.swagger.api.PrintApi;
import io.swagger.model.ModelApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class PrintController implements PrintApi{

    private final PrintUsecase printUsecase;

    @Override
    public ResponseEntity<ModelApiResponse> printTicket(String ticketId) {
        boolean execute = printUsecase.execute(ticketId);
        if(execute) {
            ModelApiResponse body = new ModelApiResponse();
            return ResponseEntity.ok(body.code(0).type("type").message("OK"));
        }
        return null;
    }
}
