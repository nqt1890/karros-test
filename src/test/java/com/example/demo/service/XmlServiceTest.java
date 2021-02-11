package com.example.demo.service;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.GPS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlServiceTest {
	@Autowired
	XmlService xmlService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void xmlFileToString() throws IOException {
		File file = new File("sample/sample.gpx");
		InputStream in = new FileInputStream(file);
		String content = xmlService.xmlFileToString(in);
		
		System.out.println(content);
		assertNotNull(content);
	}

	@Test
	public void deserializeFromXmlString() throws IOException {
		File file = new File("sample/sample.gpx");
		InputStream in = new FileInputStream(file);
		String content = xmlService.xmlFileToString(in);
		
		GPS gps = xmlService.deserializeFromXmlString(content);
		
		assertNotNull(gps);
		assertNotNull(gps.getMetadata());
		assertNotNull(gps.getTrk());
		assertNotNull(gps.getWpt());
	}

}
