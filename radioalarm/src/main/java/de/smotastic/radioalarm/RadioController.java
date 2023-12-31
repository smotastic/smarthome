package de.smotastic.radioalarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/volume/{gain}")
    public ResponseEntity<String> volume(@PathVariable Float gain) {
        radioPlayer.setVolume(gain);
        return ResponseEntity.of(Optional.of("OK"));
    }

}
