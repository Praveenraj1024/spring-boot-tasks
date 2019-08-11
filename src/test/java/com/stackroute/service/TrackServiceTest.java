package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
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
    private Track track;

    //Create a mock for TrackRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImplementation trackServiceImplementation;
    private List<Track> list = null;


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

    @After
    public void tearDown() {
        track =null;
        list = null;

    }

    @Test
    public void givenTrackShouldSavedAndReturnTheSameTrack() throws TrackAlreadyExistsException, Exception {

        when(trackRepository.save((Track) any())).thenReturn(track);
        when(trackRepository.existsById(track.getId())).thenReturn(false);
        Track savedTrack = trackServiceImplementation.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that TrackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);
        verify(trackRepository, times(1)).existsById(track.getId());


    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackAndTrackIdShouldReturnException() throws TrackAlreadyExistsException, Exception {
        trackRepository.save(track);
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        Track savedTrack = trackServiceImplementation.saveTrack(track);
        Track savedTrack1 = trackServiceImplementation.saveTrack(track);
        //Assert.assertEquals(user,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void givenTrackIdShouldReturnTrack() throws TrackNotFoundException, Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.getTrackById(track.getId());
        Assert.assertEquals(track, savedTrack);
    }

    @Test(expected = TrackNotFoundException.class)
    public void givenTrackIdShouldReturnException() throws TrackNotFoundException, Exception {
        Track savedTrack = trackServiceImplementation.getTrackById(track.getId());
        trackServiceImplementation.deleteTrackById(track.getId());
        trackServiceImplementation.updateTrack(track);
    }

    @Test
    public void givenTrackIdShouldReturnDeletedTrack() throws TrackNotFoundException, Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.deleteTrackById(track.getId()).get();
        Assert.assertEquals(track, savedTrack);
    }

    @Test
    public void givenTrackIdShouldReturnUpdatedTrack() throws TrackNotFoundException, Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackServiceImplementation.deleteTrackById(track.getId()).get();
        Assert.assertEquals(track, savedTrack);
    }

    @Test
    public void givenTrackNameShouldReturnListOfTracks() throws TrackNotFoundException, Exception {
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        when(trackRepository.getTracksByName(track.getName())).thenReturn(trackList);
        List<Track> actualTrackList = trackServiceImplementation.getTracksByName(track.getName());
        Assert.assertEquals(trackList, actualTrackList);
    }


    @Test(expected = TrackNotFoundException.class)
    public void givenTrackNameShouldReturnException() throws TrackNotFoundException, Exception {
        trackServiceImplementation.getTracksByName("yedho onnu");
    }



    @Test
    public void shouldReturnTheSavedListTracks () throws Exception{

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackServiceImplementation.getAllTracks();
        Assert.assertEquals(list, userlist);
    }




}
