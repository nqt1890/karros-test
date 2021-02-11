package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.GPS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPSRepositoryTest {
	@Autowired
	GPSRepository gpsRepo;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void whenSaved_thenFindsByUserId() {
		GPS gps1 = new GPS(null, 1, "", "user 1", null, null, null);
		GPS gps2 = new GPS(null, 2, "", "user 2", null, null, null);
		gpsRepo.save(gps1);
		gpsRepo.save(gps2);
		
		GPS gpsRs = gpsRepo.findByUserId(2);
		
		assertNotNull(gpsRs);
		assertEquals("user 2", gpsRs.getCreator());
	}

}


