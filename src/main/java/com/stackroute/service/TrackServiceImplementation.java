package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Used to mark a class as the service provider.
public class TrackServiceImplementation implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    //Used to inject the dependency automatically.
    public TrackServiceImplementation(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    //Used to override the parent class method, and to notify the mistakes.
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public Track getTrackById(int id) {
        Track retrievedTrack = trackRepository.findById(id).get();
        return retrievedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        List<Track> listTrack = trackRepository.findAll();
        return listTrack;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) {
        Optional<Track> retrievedTrack = trackRepository.findById(id);
        if (retrievedTrack.isPresent()){
//            Track track = getTrackById(id);
            trackRepository.deleteById(id);
//            System.out.println(retrievedTrack.get().getName());
//            return track;
        }
//        System.out.println(retrievedTrack.get().getName());
        return retrievedTrack;
    }

    @Override
    public Track updateTrack(int id) {
//        trackRepository
        Track track = trackRepository.findById(id).get();
        return track;
    }
}
