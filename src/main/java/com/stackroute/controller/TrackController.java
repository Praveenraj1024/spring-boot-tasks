package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//Creates RESTful web services
//Maps the request data to the request handler method
//Converts the response body into json or xml response.
@RequestMapping("api/v1/")
//Used to map web request into specific classes or methods.
public class TrackController {

    private TrackService trackService;

    @Autowired
    //Used to inject the dependency automatically.
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    //Used to map the request and requestmethod into specific method.
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException, Exception {
        //@RequestBody --> Used to convert the request body into the domain object.
        //Returns the Track object as the response for the given request.
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
    }

    /**
     * @param id stores the track id,
     * @return returns the track which matches with the given id.
     */
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException, Exception {
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        Track retrievedTrack = trackService.getTrackById(id);
        return new ResponseEntity<>(retrievedTrack, HttpStatus.FOUND);
    }

    /**
     * @return the response entity with the track list.
     */
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() throws Exception{
        List<Track> trackList = trackService.getAllTracks();
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }

    /**
     * @param id stores the track id,
     * @return the response entity with track object or null.
     */
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException, Exception {
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        Optional<Track> optionalTrack = trackService.deleteTrackById(id);
        return new ResponseEntity<>(optionalTrack.get(), HttpStatus.OK);
    }

    /**
     * @param track stores the track object which is created by using the data in the request body
     * @return the response entity with the updated track.
     */
    @PutMapping("track")
    public ResponseEntity<?> updateTrackById(@RequestBody Track track) throws TrackNotFoundException, Exception {
        Track updatedTrack = trackService.updateTrack(track);
        return new ResponseEntity<>(updatedTrack, HttpStatus.OK);

    }

    /**
     * @param name stores the track name which we want to search.
     * @return the response with selected track list.
     */
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) throws TrackNotFoundException, Exception {
        List<Track> trackList = trackService.getTracksByName(name);
        return new ResponseEntity<>(trackList, HttpStatus.FOUND);
    }

}
