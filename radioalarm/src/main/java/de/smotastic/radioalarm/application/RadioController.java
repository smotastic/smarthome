package de.smotastic.radioalarm.application;

import de.smotastic.radioalarm.data.models.VolumeControlResponse;
import de.smotastic.radioalarm.domain.RadioPlayerUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RadioController {

    private final RadioPlayerUsecase radioPlayer;

    @PostMapping(value = {"/play", "/play/{id}"})
    public ResponseEntity<String> play(@PathVariable Optional<Long> id) {
        radioPlayer.play(id.orElse(1L));
        return ResponseEntity.of(Optional.of("OK"));
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        radioPlayer.stop();
        return ResponseEntity.of(Optional.of("OK"));
    }

    @PostMapping("/volume/decrease/{value}")
    public ResponseEntity<VolumeControlResponse> volumeDecrease(@PathVariable(required = false) Optional<Float> value) {
        VolumeControlResponse volumeControlResponse = radioPlayer.decrease(value);
        return ResponseEntity.of(Optional.of(volumeControlResponse));
    }

    @PostMapping("/volume/increase/{value}")
    public ResponseEntity<VolumeControlResponse> volumeIncrease(@PathVariable(required = false) Optional<Float> value) {
        VolumeControlResponse volumeControlResponse = radioPlayer.increase(value);
        return ResponseEntity.of(Optional.of(volumeControlResponse));
    }

    @GetMapping("/volume")
    public ResponseEntity<Float> volumeGet() {
        return ResponseEntity.of(Optional.of(radioPlayer.getVolume()));
    }
}
