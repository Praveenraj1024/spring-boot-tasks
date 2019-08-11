package com.stackroute.repository;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setId(100);
        track.setName("Vetti Vetti");
        track.setComments("Ajith song");
        trackRepository.save(track);
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
        track = null;
    }


    @Test
    public void givenTrackShouldReturnSavedTrackId() {
        trackRepository.save(track);
        Track savedTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(100, savedTrack.getId());
    }

    @Test
    public void givenTrackShouldReturnDifferentId(){
        Track testTrack = new Track(100,"kokku para para", "Intro song");

        trackRepository.save(testTrack);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void givenTracksShouldReturnListOfSavedTrack(){
        Track track1 = new Track(100, "Van varuvan", "love song");
        Track track2 = new Track(103, "Yaro yarodi", "intro song");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        assertEquals(track1.getId(),list.get(0).getId());

    }

    @Test
    public void givenIdShouldReturnFalse() {
        assertFalse(trackRepository.existsById(10));
    }

    @Test
    public void givenIdsShouldReturnTrue() {
        Track testTrack = new Track(100,"kokku para para", "Intro song");
        trackRepository.save(testTrack);
        assertTrue(trackRepository.existsById(testTrack.getId()));
    }

    @Test
    public void givenTrackAndIdShouldReturnTheTrack() {
        Track testTrack = new Track(110,"kokku para para", "Intro song");
        trackRepository.save(testTrack);
        assertEquals(testTrack, trackRepository.findById(testTrack.getId()).get());

    }

    @Test
    public void givenTrackAndTrackIdShouldDeleteTrackAndReturnFalse() {
        Track testTrack = new Track(110,"kokku para para", "Intro song");
        trackRepository.delete(testTrack);
        assertFalse(trackRepository.existsById(testTrack.getId()));

    }

    @Test
    public void givenTrackAndTrackIdShouldDeleteTrackAndNotReturnFalse() {
        Track testTrack = new Track(110,"kokku para para", "Intro song");
        Track anotherTestTrack = new Track(111,"kokku para para", "Intro song");
        trackRepository.delete(testTrack);
        assertTrue(trackRepository.existsById(anotherTestTrack.getId()));

    }






}