package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This class overrides the run() method to store the track details in h2 db.
 */
@Component
@Data
//To create getters and setters for all properties, to override toString(),
// to create equals, canEquals and HashCode.
@ConfigurationProperties(prefix = "track4")
//Used to get the data prefix with track4 and matches with the property name.
public class CommandLineRunnerSeedData implements CommandLineRunner {
    @Qualifier("trackService")
    //Used to particularly mention the bean name.
    private TrackRepository trackRepository;

    @Autowired
    public CommandLineRunnerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    private int id;
    private String name;
    private String comments;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        Track track1 = new Track(Integer.parseInt(environment.getProperty("track3.id")),
                                environment.getProperty("track3.name"), environment.getProperty("track3.comments"));
        Track track2 = new Track(id, name, comments);
        trackRepository.save(track1);
        trackRepository.save(track2);
    }
}
