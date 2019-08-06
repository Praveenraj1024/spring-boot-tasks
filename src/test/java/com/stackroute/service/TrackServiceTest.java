package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {
    Track track;

    //Create a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImplementation trackServiceImplementation;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(114);
        track.setName("Kadavule kadavule");
        track.setComments("love song");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void givenTrackShouldReturnTheSameTrack() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackServiceImplementation.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that TrackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTracksAndTrackIdShouldReturnException() throws TrackAlreadyExistsException {
        trackRepository.save(track);
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        Track savedTrack = trackServiceImplementation.saveTrack(track);
        Track savedTrack1 = trackServiceImplementation.saveTrack(track);
        //Assert.assertEquals(user,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void givenIdShouldReturnTrack() throws TrackNotFoundException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.getTrackById(track.getId());
        Assert.assertEquals(track, savedTrack);
    }

    @Test(expected = TrackNotFoundException.class)
    public void givenIdShouldReturnException() throws TrackNotFoundException {
        Track savedTrack = trackServiceImplementation.getTrackById(track.getId());
        trackServiceImplementation.deleteTrackById(track.getId());
        trackServiceImplementation.updateTrack(track.getId(), track);
    }

    @Test
    public void givenIdShouldReturnDeletedTrack() throws TrackNotFoundException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.deleteTrackById(track.getId()).get();
        Assert.assertEquals(track, savedTrack);
    }

    @Test
    public void givenIdShouldReturnUpdatedTrack() throws TrackNotFoundException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.deleteTrackById(track.getId()).get();
        Assert.assertEquals(track, savedTrack);
    }

    @Test
    public void givenNameShouldReturnListOfTracks() throws TrackNotFoundException {
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        when(trackRepository.getTracksByName(track.getName())).thenReturn(trackList);
        List<Track> actualTrackList = trackServiceImplementation.getTracksByName(track.getName());
        Assert.assertEquals(trackList, actualTrackList);
    }


    @Test(expected = TrackNotFoundException.class)
    public void givenNameShouldReturnException() throws TrackNotFoundException {
        trackServiceImplementation.getTracksByName("yedho onnu");
    }



    @Test
    public void getAllUser() throws Exception{

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackServiceImplementation.getAllTracks();
        Assert.assertEquals(list, userlist);
    }




}
