package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Track;
import com.example.demo.domain.Trackpoint;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackpointRepositoryTest {
	@Autowired
	TrackpointRepository trackpointRepo;
	
	@Autowired
	GPSRepository gpsRepo;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void whenSaved_thenfindAllByTrackIdOrderByTimeDesc() {
		Track tr = new Track();
		GPS gps = new GPS(null, 1, "", "user 1", null, null, tr);
		gps = gpsRepo.save(gps);
		
		List<Trackpoint> tps = new ArrayList<>();		
		for(int i = 0; i < 30; i++) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, i);
			dt = c.getTime();
			
			Trackpoint tp = new Trackpoint(1.2, 2.3, "ele: " + i, dt);
			tp.setTrack(tr);
			tps.add(tp);
		}
		trackpointRepo.saveAll(tps);
		
		
		Pageable pageable = PageRequest.of(0, 10);
		List<Trackpoint> rs = trackpointRepo.findAllByTrackIdOrderByTimeDesc(tr.getId(), pageable);
		
		assertNotNull(rs);
		assertEquals(10, rs.size());
		assertEquals("ele: 29", rs.get(0).getEle());
	}

}


