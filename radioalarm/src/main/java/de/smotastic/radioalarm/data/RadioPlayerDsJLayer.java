package de.smotastic.radioalarm.data;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;

@Slf4j
@Component
public class RadioPlayerDsJLayer implements RadioPlayerDs {

    AdvancedPlayer player;
    private AudioDevice device;
    private FloatControl volControl;

    @Override
    public void play(String url) {
        if (player != null) {
            stop();
        }
        try {
            InputStream input = new URL(url).openStream();
            BufferedInputStream buffer = new BufferedInputStream(input);
            this.device = FactoryRegistry.systemRegistry().createAudioDevice();
            this.player = new AdvancedPlayer(buffer, this.device);
            this.player.play();
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        log.info("Stop");
        player.close();
        volControl = null;
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

    @Override
    public VolumeControlResponse decrease(Float decrease) {
        log.info("Reduce Volume");
        initVolume();
        if (this.volControl != null) {
            float oldVol = volControl.getValue();
            float targetVol = oldVol - decrease;
            float newVol = Math.min(Math.max(targetVol, volControl.getMinimum()), volControl.getMaximum());
            log.info("Reducing from {} to {}", volControl.getValue(), targetVol);
            volControl.setValue(newVol);
            return new VolumeControlResponse("Volume reduced", newVol, oldVol);
        }
        throw new RuntimeException("Volume could not be decreased");
    }

    @Override
    public VolumeControlResponse increase(Float increase) {
        log.info("Increase Volume");
        initVolume();
        if (this.volControl != null) {
            float oldVol = volControl.getValue();
            float targetVol = oldVol + increase;
            float newVol = Math.min(Math.max(targetVol, volControl.getMinimum()), volControl.getMaximum());
            log.info("Increasing from {} to {}", volControl.getValue(), targetVol);
            volControl.setValue(newVol);
            return new VolumeControlResponse("Volume increased", newVol, oldVol);
        }
        throw new RuntimeException("Volume could not be increased");
    }

    @Override
    public Float getVolume() {
        log.info("Receive Volume");
        initVolume();
        if (this.volControl != null) {
            return volControl.getValue();
        }
        throw new RuntimeException("Volume could not be received");
    }
}
