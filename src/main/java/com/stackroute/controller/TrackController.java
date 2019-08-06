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
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        //@RequestBody --> Used to convert the request body into the domain object.
        //Returns the Track object as the response for the given request.
        try {
            Track savedTrack = trackService.saveTrack(track);
            return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param id stores the track id,
     * @return returns the track which matches with the given id.
     */
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id){
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        try{
            Track retrievedTrack = trackService.getTrackById(id);
            return new ResponseEntity<>(retrievedTrack, HttpStatus.FOUND);
        }catch(TrackNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return the response entity with the track list.
     */
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack(){
        try {
            List<Track> trackList = trackService.getAllTracks();
            return new ResponseEntity<>(trackList, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param id stores the track id,
     * @return the response entity with track object or null.
     */
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) {
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        try {
            Optional<Track> optionalTrack = trackService.deleteTrackById(id);
            return new ResponseEntity<Track>(optionalTrack.get(), HttpStatus.OK);

        }catch (TrackNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param id stores the track id,
     * @param track stores the track object which is created by using the data in the request body
     * @return the response entity with the updated track.
     */
    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track track){
        try {
            Track updatedTrack = trackService.updateTrack(id, track);
            return new ResponseEntity<>(updatedTrack, HttpStatus.OK);
        }catch (TrackNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * @param name stores the track name which we want to search.
     * @return the response with selected track list.
     */
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name){
        try {
            List<Track> trackList = trackService.getTracksByName(name);
            return new ResponseEntity<>(trackList, HttpStatus.FOUND);
        }catch (TrackNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
