package de.smotastic.radioalarm.domain;

import de.smotastic.radioalarm.data.models.VolumeControlResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RadioPlayerUsecase {

    private final RadioPlayerPort radioPlayerPort;
    private Thread startingThread;

    public void play(Long id) {
        Runnable task = () -> radioPlayerPort.play(id);
        if (startingThread == null || !startingThread.isAlive()) {
            startingThread = new Thread(task);
            startingThread.start();
        } else {
            stop();
        }
    }

    public void stop() {
        radioPlayerPort.stop();
        try {
            startingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public VolumeControlResponse increase(Optional<Float> increase) {
        return radioPlayerPort.increase(increase.orElse(1.0f));
    }

    public VolumeControlResponse decrease(Optional<Float> decrease) {
        return radioPlayerPort.decrease(decrease.orElse(0.1f));
    }

    public Float getVolume() {
        return radioPlayerPort.getVolume();
    }
}
