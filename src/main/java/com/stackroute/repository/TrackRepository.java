package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    /**
     * @param trackName stores the track name which we want to search
     * @return the list of tracks which matches with the given name.
     */
    @Query("select t from Track t where t.name like ?1")
    //Used to execute the query and it will return the result.
    List<Track> getTracksByName(String trackName);
}
