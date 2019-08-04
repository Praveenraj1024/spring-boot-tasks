package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("dummy")
public class TrackDummyServiceImplementation implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        return null;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        return Optional.empty();
    }

    @Override
    public Track updateTrack(int id, Track track) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> getTracksByName(String trackName) throws TrackNotFoundException {
        return null;
    }
}
