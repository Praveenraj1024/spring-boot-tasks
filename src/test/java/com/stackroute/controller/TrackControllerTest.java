package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setId(115);
        track.setName("Kanne");
        track.setComments("love song");
        list = new ArrayList();
        list.add(track);
    }

    @After
    public void tearDown() {
        track = null;
        list = null;
    }

    @Test
    public void givenUrlShouldReturnTheTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        verify(trackService, times(1)).saveTrack(track);


    }

    @Test
    public void givenUrlAndTrackShouldReturnException() throws TrackAlreadyExistsException, Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUrlAndTrackShouldReturnServerException() throws TrackAlreadyExistsException, Exception {
        when(trackService.saveTrack(any())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUrlShouldReturnListOfTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getAllTracks();

    }

    @Test
    public void givenUrlShouldReturnException() throws Exception {
        when(trackService.getAllTracks()).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenUrlWithIdShouldReturnTrack() throws TrackNotFoundException, Exception {
        when(trackService.getTrackById(anyInt())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/115")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getTrackById(track.getId());
    }

    @Test
    public void givenUrlWithIdShouldReturnException() throws TrackNotFoundException, Exception {
        when(trackService.getTrackById(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/116")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUrlWithIdShouldReturnServerException() throws TrackNotFoundException, Exception {
        when(trackService.getTrackById(anyInt())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/116")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void givenUrlWithIdShouldReturnDeletedTrack() throws TrackNotFoundException, Exception {
        when(trackService.deleteTrackById(anyInt())).thenReturn(Optional.of(track));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/115")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).deleteTrackById(track.getId());
    }

    @Test
    public void givenUrlWithIdShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/116")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void givenUrlWithIdShouldReturnTheServerException() throws TrackNotFoundException, Exception {
        when(trackService.deleteTrackById(anyInt())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/116")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUrlWithNameShouldReturnTrack() throws TrackNotFoundException, Exception {
        when(trackService.getTracksByName(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/kanne")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getTracksByName(track.getName());
    }

    @Test
    public void givenUrlWithNameShouldReturnException() throws TrackNotFoundException, Exception {
        when(trackService.getTracksByName(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/yedho onnu solluvom")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUrlWithNameShouldReturnServerException() throws TrackNotFoundException, Exception {
        when(trackService.getTracksByName(any())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/yedho onnu solluvom")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void givenUrlAndTrackShouldReturnUpdatedTrack() throws TrackNotFoundException, Exception {
        when(trackService.updateTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenUrlAndTrackShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
        when(trackService.updateTrack(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void givenUrlAndTrackShouldReturnTheServerException() throws TrackNotFoundException, Exception {
        when(trackService.updateTrack(any())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
