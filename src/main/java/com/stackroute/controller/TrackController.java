package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id){
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        Track retrievedTrack = trackService.getTrackById(id);
        return new ResponseEntity<>(retrievedTrack, HttpStatus.OK);
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack(){
        List<Track> trackList = trackService.getAllTracks();
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) {
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        trackService.deleteTrackById(id);
        Track track = trackService.deleteTrackById(id);
        System.out.println(track.getName());
        return new ResponseEntity<Track>(track, HttpStatus.OK);
    }

    @PatchMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@RequestBody @PathVariable int id){
        //Used to extract the data from query parameter.
        //Returns the User object as the response for the given request.
        Track track = trackService.updateTrack(id);
        return new ResponseEntity<>(track, HttpStatus.OK);

    }

}
