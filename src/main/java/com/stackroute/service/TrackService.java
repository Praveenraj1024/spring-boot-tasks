package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    /**
     * @param track stores the track object
     * @return returns the track object which is saved in db.
     */
    public Track saveTrack(Track track) throws TrackAlreadyExistsException, Exception;

    /**
     * @param id stores the track id.
     * @return returns the track object when the given id matches with the saved tracks.
     */
    public Track getTrackById(int id) throws TrackNotFoundException, Exception;

    /**
     * @return returns all tracks in list.
     */
    public List<Track> getAllTracks() throws Exception;

    /**
     * @param id stores the track id which we want to delete,
     * @return the track object if the given id matches with any saved track objects.
     */
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException, Exception;

    /**
     * @param track stores the track object,
     * @return the updated track object.
     */
    public Track updateTrack(Track track) throws TrackNotFoundException, Exception;

    /**
     * @param trackName stores the track name of the track,
     * @return returns the list of tracks whose track name matches with the given trackname.
     */
    public List<Track> getTracksByName(String trackName) throws TrackNotFoundException, Exception;

}
