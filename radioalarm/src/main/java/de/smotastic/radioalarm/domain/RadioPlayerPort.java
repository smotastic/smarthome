package de.smotastic.radioalarm.domain;

import de.smotastic.radioalarm.data.models.VolumeControlResponse;

public interface RadioPlayerPort {
    void play(Long id);
    void stop();
    VolumeControlResponse increase(Float increase);
    VolumeControlResponse decrease(Float decrease);
    Float getVolume();
}
