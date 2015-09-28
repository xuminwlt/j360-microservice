package me.j360.boot.microservice.order.test.repo;

import me.j360.boot.microservice.order.J360Configuration;
import me.j360.boot.microservice.order.entity.Express;
import me.j360.boot.microservice.order.entity.Track;
import me.j360.boot.microservice.order.repository.ExpressRepository;
import me.j360.boot.microservice.order.repository.TrackRepository;
import org.hibernate.id.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.test.repo.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 10:59
 * 说明：
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J360Configuration.class)
public class ExpressRepositoryTest {

    @Autowired
    private ExpressRepository expressRepository;
    @Autowired
    private TrackRepository trackRepository;

    @Test
    public void expressCreate(){
        Track track = new Track();
        track.setDate(new Date());
        track.setMessage("取件");
        trackRepository.save(track);

        List<Track> trackList = new ArrayList<Track>();
        trackList.add(track);

        Express express = new Express();
        express.setCost(BigDecimal.ONE);
        express.setNo(UUIDGenerator.UUID_GEN_STRATEGY);
        express.setTracks(trackList);
        expressRepository.save(express);
        assertNotNull(express.getId());

        assertNotNull(track.getId());
        assertNotNull(express.getTracks());
    }

    @Test
    public void findByNoTest(){
        String no = UUIDGenerator.UUID_GEN_STRATEGY;
        Express express = new Express();
        express.setCost(BigDecimal.ONE);
        express.setNo(no);
        expressRepository.save(express);

        Express express1 = expressRepository.findByNoAllIgnoringCase(no);
        assertEquals(no,express1.getNo());
    }



}
