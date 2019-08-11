package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Used to mark a class as the service provider.
@Profile("service")
public class TrackServiceImplementation implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    //Used to inject the dependency automatically.
    public TrackServiceImplementation(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    //Used to override the parent class method, and to notify the mistakes.
    public Track saveTrack(Track track) throws TrackAlreadyExistsException, Exception {
        if (trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("A Track with the given id" +
                    " is already exist in db");
        }
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException, Exception {
        if (trackRepository.existsById(id)){
            Track retrievedTrack = trackRepository.findById(id).get();
            return retrievedTrack;
        }
        throw new TrackNotFoundException("Their is no track with the specified id");
    }

    @Override
    public List<Track> getAllTracks() throws Exception{
        List<Track> listTrack = trackRepository.findAll();
        return listTrack;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException, Exception{
        if (trackRepository.existsById(id)){
            Optional<Track> retrievedTrack = trackRepository.findById(id);
            trackRepository.deleteById(id);
            return retrievedTrack;

        }
        throw new TrackNotFoundException("Unable to delete, because their is no track in db with the given id");

    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException, Exception{
        if (trackRepository.existsById(track.getId())) {
            Track trackBeforeUpdate = trackRepository.findById(track.getId()).get();
            track.setComments(track.getComments());
            track.setName(track.getName());
            return trackRepository.save(track);
        }
        throw new TrackNotFoundException("Their is no track with the specified id");
    }

    @Override
    public List<Track> getTracksByName(String trackName) throws TrackNotFoundException, Exception{
        List<Track> trackList = trackRepository.getTracksByName(trackName);
        if (trackList.isEmpty() || trackList == null){
            throw new TrackNotFoundException("Their is no track with the specified track name");
        }
        return trackList;
    }

}
