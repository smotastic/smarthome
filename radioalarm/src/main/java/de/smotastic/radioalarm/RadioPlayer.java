package de.smotastic.radioalarm;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
public class RadioPlayer {

    Player player;
    Thread startingThread;

    public RadioPlayer() {
        try {
            InputStream input = new URL("http://mp3.ffh.de/radioffh/hqlivestream.mp3").openStream();
            BufferedInputStream buffer = new BufferedInputStream(input);
            player = new Player(buffer);

            startingThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception ex) {
            log.error("Player init failed", ex);
        }
    }

    public void play() {
        log.info("PLAY");
        startingThread.start();
    }

    public void stop() {
        player.close();
        startingThread.interrupt();
    }

}
