package de.smotastic.radioalarm.domain;

import de.smotastic.radioalarm.data.RadioPlayerDs;
import de.smotastic.radioalarm.data.VolumeControlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RadioPlayerUsecase {

    private final RadioPlayerDs radioPlayerDs;

    private Thread startingThread;


    public void play(String url) {
        Runnable task = () -> radioPlayerDs.play(url);
        if (startingThread == null || !startingThread.isAlive()) {
            startingThread = new Thread(task);
            startingThread.start();
        } else {
            stop();
        }
    }

    public void stop() {
        radioPlayerDs.stop();
        try {
            startingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public VolumeControlResponse increase(Optional<Float> increase) {
        return radioPlayerDs.increase(increase.orElse(1.0f));
    }

    public VolumeControlResponse decrease(Optional<Float> decrease) {
        return radioPlayerDs.decrease(decrease.orElse(0.1f));
    }

    public Float getVolume() {
        return radioPlayerDs.getVolume();
    }
}
