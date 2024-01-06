package de.smotastic.radioalarm.data;

public interface RadioPlayerDs {
    void play(String url);
    void stop();
    VolumeControlResponse increase(Float increase);
    VolumeControlResponse decrease(Float decrease);
    Float getVolume();
}
