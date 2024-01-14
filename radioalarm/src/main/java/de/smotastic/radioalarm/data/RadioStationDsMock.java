package de.smotastic.radioalarm.data;

import de.smotastic.radioalarm.data.models.RadioStationModel;

import java.util.Map;
import java.util.Optional;

public class RadioStationDsMock implements RadioStationDs {

    private static final Map<Long, RadioStationModel> radios = Map.of(
            1L, new RadioStationModel("FFH", "http://mp3.ffh.de/radioffh/hqlivestream.mp3"),
            2L, new RadioStationModel("FFH Leider geil","http://mp3.ffh.de/ffhchannels/hqvoting.mp3"),
            3L, new RadioStationModel("FFH Top 40","http://mp3.ffh.de/ffhchannels/hqtop40.mp3"),
            4L, new RadioStationModel("FFH Schlager","http://mp3.ffh.de/ffhchannels/hqschlager.mp3"),
            5L, new RadioStationModel("80s","http://streams.80s80s.de/web/mp3-192/streams.80s80s.de/"),
            6L, new RadioStationModel("Rock","http://mp3channels.webradio.rockantenne.de/rockantenne"),
            7L, new RadioStationModel("90s","https://mp3.harmonyfm.de/hrmplus/hq90er.mp3")
    );

    @Override
    public Optional<RadioStationModel> byId(Long id) {
        return Optional.ofNullable(radios.get(id));
    }
}
