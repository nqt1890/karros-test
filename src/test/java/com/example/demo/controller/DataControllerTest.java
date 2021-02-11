package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Track;
import com.example.demo.domain.Trackpoint;
import com.example.demo.repository.GPSRepository;
import com.example.demo.repository.TrackpointRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(DataController.class)
public class DataControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	GPSRepository gpsRepo;
	
	@MockBean
	TrackpointRepository trackpointRepo;

	@Test
	public void lastestTracksTest() throws Exception {
		Track tr = new Track();
		tr.setId(1L);
		GPS gps = new GPS(null, 1, "", "user 1", null, null, tr);

		List<Trackpoint> tps = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, i);
			dt = c.getTime();

			Trackpoint tp = new Trackpoint(1.2, 2.3, "ele: " + i, dt);
			tp.setTrack(tr);
			tps.add(tp);
		}
		
		Mockito.when(gpsRepo.findByUserId(Mockito.anyLong())).thenReturn(gps);
		Mockito.when(trackpointRepo.findAllByTrackIdOrderByTimeDesc(Mockito.anyLong(), Mockito.any())).thenReturn(tps);
		
		String uri = "/user/1/lastesttrack?page=0";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		ObjectMapper objectMapper = new ObjectMapper();
		List<Trackpoint> rs = objectMapper.readValue(content, List.class);
		
		assertNotNull(rs);
		assertEquals(10, rs.size());
	}

	
	@Test
	public void detail() throws Exception {
		Track tr = new Track();
		tr.setId(1L);
		GPS gps = new GPS(null, 1, "1.1", "user 1", null, null, tr);

		Mockito.when(gpsRepo.findByUserId(Mockito.anyLong())).thenReturn(gps);
		
		String uri = "/user/1/detail";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		ObjectMapper objectMapper = new ObjectMapper();
		GPS rs = objectMapper.readValue(content, GPS.class);
		
		assertNotNull(rs);
		assertEquals("1.1", rs.getVersion());
		assertNotNull("1.1", rs.getTrk());
	}
}
