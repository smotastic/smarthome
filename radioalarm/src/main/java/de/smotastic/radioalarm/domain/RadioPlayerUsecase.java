package de.smotastic.radioalarm.domain;

import de.smotastic.radioalarm.data.models.VolumeControlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class RadioPlayerUsecase {

    private final RadioPlayerPort radioPlayerPort;
    private Thread startingThread;

    public void play(Long id) {
        log.info("Playing radio for ID {}", id);
        Runnable task = () -> radioPlayerPort.play(id);
        if (startingThread != null && startingThread.isAlive()) {
            log.info("Still playing, first stopping");
            stop();
        }
        startingThread = new Thread(task);
        startingThread.start();
    }

    public void stop() {
        log.info("Stopping Radio");
        radioPlayerPort.stop();
        try {
            startingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public VolumeControlResponse increase(Optional<Float> increase) {
        log.info("Increase Volume by {}", increase);
        return radioPlayerPort.increase(increase.orElse(1.0f));
    }

    public VolumeControlResponse decrease(Optional<Float> decrease) {
        log.info("Decrease Volume by {}", decrease);
        return radioPlayerPort.decrease(decrease.orElse(0.1f));
    }

    public Float getVolume() {
        Float volume = radioPlayerPort.getVolume();
        log.info("Receive Volume: {}", volume);
        return volume;
    }
}
