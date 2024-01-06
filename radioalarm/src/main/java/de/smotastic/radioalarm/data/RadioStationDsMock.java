package de.smotastic.radioalarm.data;

import de.smotastic.radioalarm.data.models.RadioStationModel;

import java.util.Map;
import java.util.Optional;

public class RadioStationDsMock implements RadioStationDs {

    private static final Map<Long, RadioStationModel> radios = Map.of(
            1L, new RadioStationModel("http://mp3.ffh.de/radioffh/hqlivestream.mp3"),
            2L, new RadioStationModel("http://mp3.ffh.de/ffhchannels/hqvoting.mp3"),
            3L, new RadioStationModel("http://mp3.ffh.de/ffhchannels/hqtop40.mp3"),
            4L, new RadioStationModel("http://mp3.ffh.de/ffhchannels/hqschlager.mp3")

    );

    @Override
    public Optional<RadioStationModel> byId(Long id) {
        return Optional.ofNullable(radios.get(id));
    }
}
