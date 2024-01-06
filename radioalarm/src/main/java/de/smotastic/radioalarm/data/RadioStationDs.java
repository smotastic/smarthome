package de.smotastic.radioalarm.data;

import de.smotastic.radioalarm.data.models.RadioStationModel;

import java.util.Optional;

public interface RadioStationDs {
    Optional<RadioStationModel> byId(Long id);
}
