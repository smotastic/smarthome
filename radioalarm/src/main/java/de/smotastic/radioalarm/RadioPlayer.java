package de.smotastic.radioalarm;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
public class RadioPlayer {

    Player player;
    Thread startingThread;
    Runnable task;

    public RadioPlayer() {
        task = () -> {
            try {
                InputStream input = new URL("http://mp3.ffh.de/radioffh/hqlivestream.mp3").openStream();
                BufferedInputStream buffer = new BufferedInputStream(input);
                player = new Player(buffer);
                player.play();
            } catch (JavaLayerException | IOException e) {
                throw new RuntimeException(e);
            }
        };

    }

    public void play() {
        log.info("PLAY");
        if (startingThread == null || !startingThread.isAlive()) {
            startingThread = new Thread(task);
            startingThread.start();
        }
    }

    public void stop() {
        player.close();
        try {
            startingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
