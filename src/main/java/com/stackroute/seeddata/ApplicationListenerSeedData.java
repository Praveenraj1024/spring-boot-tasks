package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {
    private TrackService trackService;

    @Autowired
    public ApplicationListenerSeedData(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        Track track1 = new Track(11, "Penne Penne", "A nice song");
        Track track2 = new Track(12, "Kadhaley Kadhaley", "melody song");
        Track track3 = new Track(13, "Karu", "opening song");
        try{
            trackService.saveTrack(track1);
            trackService.saveTrack(track2);
            trackService.saveTrack(track3);
        }catch (TrackAlreadyExistsException ex){
            ex.printStackTrace();
        }
    }
}
