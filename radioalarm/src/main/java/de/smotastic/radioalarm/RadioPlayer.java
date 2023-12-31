package de.smotastic.radioalarm;

import de.smotastic.radioalarm.data.VolumeControlResponse;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class RadioPlayer {

    AdvancedPlayer player;
    Thread startingThread;
    Runnable task;
    private AudioDevice device;
    private FloatControl volControl;

    public RadioPlayer() {
        task = () -> {
            try {
                InputStream input = new URL("http://mp3.ffh.de/radioffh/hqlivestream.mp3").openStream();
                BufferedInputStream buffer = new BufferedInputStream(input);
                this.device = FactoryRegistry.systemRegistry().createAudioDevice();
                player = new AdvancedPlayer(buffer, device);

                player.play();
            } catch (JavaLayerException | IOException e) {
                throw new RuntimeException(e);
            }
        };

    }

    private void initVolume() {
        if (this.volControl == null) {
            Class<JavaSoundAudioDevice> clazz = JavaSoundAudioDevice.class;
            Field[] fields = clazz.getDeclaredFields();
            try {
                SourceDataLine source;
                for (Field field : fields) {
                    if ("source".equals(field.getName())) {
                        field.setAccessible(true);
                        source = (SourceDataLine) field.get(this.device);
                        if (source != null) {
                            field.setAccessible(false);
                            this.volControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Setting volume went wrong", e);
                throw new RuntimeException(e);
            }
        }
    }

    public VolumeControlResponse decreaseVolumn(Optional<Float> gain) {
        log.info("Reduce Volume");
        initVolume();
        if (this.volControl != null) {
            float oldVol = volControl.getValue();
            float targetVol = oldVol - gain.orElse(1.0f);
            float newVol = Math.min(Math.max(targetVol, volControl.getMinimum()), volControl.getMaximum());
            log.info("Reducing from {} to {}", volControl.getValue(), targetVol);
            volControl.setValue(newVol);
            return new VolumeControlResponse("Volume reduced", newVol, oldVol);
        }
        throw new RuntimeException("Volume could not be decreased");
    }

    public VolumeControlResponse increaseVolumn(Optional<Float> gain) {
        log.info("Increase Volume");
        initVolume();
        if (this.volControl != null) {
            float oldVol = volControl.getValue();
            float targetVol = oldVol + (gain.orElse(0.1f));
            float newVol = Math.min(Math.max(targetVol, volControl.getMinimum()), volControl.getMaximum());
            log.info("Increasing from {} to {}", volControl.getValue(), targetVol);
            volControl.setValue(newVol);
            return new VolumeControlResponse("Volume increased", newVol, oldVol);
        }
        throw new RuntimeException("Volume could not be increased");
    }

   public Float receiveVolumn() {
       log.info("Receive Volume");
       initVolume();
       if (this.volControl != null) {
           return volControl.getValue();
       }
       throw new RuntimeException("Volume could not be received");
   }



    public void play() {
        log.info("Play");
        if (startingThread == null || !startingThread.isAlive()) {
            startingThread = new Thread(task);
            startingThread.start();
        }
    }

    public void stop() {
        log.info("Stop");
        player.close();
        volControl = null;
        try {
            startingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
