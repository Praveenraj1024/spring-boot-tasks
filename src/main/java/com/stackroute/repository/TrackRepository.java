package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    /**
     * @param trackName stores the track name which we want to search
     * @return the list of tracks which matches with the given name.
     */

    List<Track> getTracksByName(String trackName);
}
