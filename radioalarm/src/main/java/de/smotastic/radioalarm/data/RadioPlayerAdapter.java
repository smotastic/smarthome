package de.smotastic.radioalarm.data;

import de.smotastic.radioalarm.data.models.RadioStationModel;
import de.smotastic.radioalarm.data.models.VolumeControlResponse;
import de.smotastic.radioalarm.domain.RadioPlayerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class RadioPlayerAdapter implements RadioPlayerPort {

    private final RadioPlayerDs radioPlayerDs;
    private final RadioStationDs radioStationDs;

    @Override
    public void play(Long id) {
        Optional<RadioStationModel> radioStationModel = radioStationDs.byId(id);
        if (radioStationModel.isPresent()) {
            log.info("Found Radio {} for ID {}", radioStationModel.get().getName(), id);
            radioPlayerDs.play(radioStationModel.get().getUrl());
        } else {
            log.warn("No Radio Station found for ID {}", id);
        }
    }

    @Override
    public void stop() {
        radioPlayerDs.stop();
    }

    @Override
    public VolumeControlResponse increase(Float increase) {
        return radioPlayerDs.increase(increase);
    }

    @Override
    public VolumeControlResponse decrease(Float decrease) {
        return radioPlayerDs.decrease(decrease);
    }

    @Override
    public Float getVolume() {
        return radioPlayerDs.getVolume();
    }
}
