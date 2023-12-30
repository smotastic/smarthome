package de.smotastic.radioalarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RadioController {

    @Autowired
    RadioPlayer radioPlayer;

    @PostMapping("/play")
    public ResponseEntity<String> play() {
        radioPlayer.play();
        return ResponseEntity.of(Optional.of("OK"));
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        radioPlayer.stop();
        return ResponseEntity.of(Optional.of("OK"));
    }

}
