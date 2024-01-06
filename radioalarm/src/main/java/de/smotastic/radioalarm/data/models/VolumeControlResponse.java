package de.smotastic.radioalarm.data.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VolumeControlResponse {
    private final String message;
    private final Float newVolume;
    private final Float oldVolume;
}
