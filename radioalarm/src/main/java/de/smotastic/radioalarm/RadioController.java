package de.smotastic.radioalarm;

import de.smotastic.radioalarm.data.VolumeControlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/volume/decrease/{value}")
    public ResponseEntity<VolumeControlResponse> volumeDecrease(@PathVariable(required = false) Optional<Float> value) {
        VolumeControlResponse volumeControlResponse = radioPlayer.decreaseVolumn(value);
        return ResponseEntity.of(Optional.of(volumeControlResponse));
    }

    @PostMapping("/volume/increase/{value}")
    public ResponseEntity<VolumeControlResponse> volumeIncrease(@PathVariable(required = false) Optional<Float> value) {
        VolumeControlResponse volumeControlResponse = radioPlayer.increaseVolumn(value);
        return ResponseEntity.of(Optional.of(volumeControlResponse));
    }

    @GetMapping("/volume")
    public ResponseEntity<Float> volumeGet() {
        return ResponseEntity.of(Optional.of(radioPlayer.receiveVolumn()));
    }
}
