package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public class TrackDummyServiceImplementation implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException, Exception {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException, Exception {
        return null;
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        return null;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException, Exception {
        return Optional.empty();
    }

    @Override
    public Track updateTrack(int id, Track track) throws TrackNotFoundException, Exception {
        return null;
    }

    @Override
    public List<Track> getTracksByName(String trackName) throws TrackNotFoundException, Exception {
        return null;
    }
}
