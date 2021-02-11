package com.example.demo.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.Trackpoint;
import com.example.demo.domain.Waypoint;
import com.example.demo.repository.GPSRepository;
import com.example.demo.service.XmlService;

@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	XmlService xmlService;

	@MockBean
	GPSRepository gpsRepo;

	@Test
	public void uploadPage() throws Exception {
		String uri = "/upload";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	@Test
	public void uploadFile() throws Exception {
		Track tr = new Track();
		List<Trackpoint> tps = new ArrayList<>();
		tps.add(new Trackpoint());
		tr.setTrkpt(tps);

		Metadata me = new Metadata("meta", "", "", null, new Link());
		List<Waypoint> wps = new ArrayList<>();
		wps.add(new Waypoint());

		GPS gps = new GPS(null, 1, "1.1", "user 1", me, wps, tr);

		Mockito.when(xmlService.xmlFileToString(Mockito.any())).thenReturn("");
		Mockito.when(xmlService.deserializeFromXmlString(Mockito.any())).thenReturn(gps);
		Mockito.when(gpsRepo.save(Mockito.any())).thenReturn(gps);

		MockMultipartFile firstFile = new MockMultipartFile("file", new FileInputStream(new File("sample/sample.gpx")));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.multipart("/upload").file(firstFile).param("user_id", "1")).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(302, status);
	}
}
