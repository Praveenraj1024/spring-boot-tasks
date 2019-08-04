package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class overrides the run() method to store the track details in h2 db.
 */
@Component
public class CommandLineRunnerSeedData implements CommandLineRunner {
    @Qualifier("trackService")
    //Used to particularly mention the bean name.
    private TrackRepository trackRepository;

    @Autowired
    public CommandLineRunnerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track1 = new Track(14, "Penne Penne", "A nice song");
        Track track2 = new Track(15, "Kadhaley Kadhaley", "melody song");
        Track track3 = new Track(16, "Karu", "opening song");
        trackRepository.save(track1);
        trackRepository.save(track2);
        trackRepository.save(track3);
    }
}
