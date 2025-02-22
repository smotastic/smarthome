package de.smotastic.radioalarm.data;

import de.smotastic.radioalarm.data.models.VolumeControlResponse;

public interface RadioPlayerDs {
    void play(String url);
    void stop();
    VolumeControlResponse increase(Float increase);
    VolumeControlResponse decrease(Float decrease);
    Float getVolume();
}
